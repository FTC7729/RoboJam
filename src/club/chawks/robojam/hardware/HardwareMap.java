package club.chawks.robojam.hardware;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HardwareMap implements Iterable<HardwareDevice> {
	
	//-----------------------------------------------------------------------------------
	// State
	//-----------------------------------------------------------------------------------
	public DeviceMapping<ServoController>	servoController		= new DeviceMapping<ServoController>(ServoController.class);
	public DeviceMapping<CRServo>			crservo				= new DeviceMapping<CRServo>(CRServo.class);
	
	protected Map<String, List<HardwareDevice>> allDevicesMap	= new HashMap<String, List<HardwareDevice>>();
	protected List<HardwareDevice>              allDevicesList  = null;
	protected Map<HardwareDevice, Set<String>>	deviceNames		= new HashMap<HardwareDevice, Set<String>>();
	protected Map<SerialNumber, HardwareDevice> serialNumberMap = new HashMap<SerialNumber, HardwareDevice>();

	public final List<DeviceMapping<? extends HardwareDevice>> allDeviceMappings;
	
	protected final Object lock = new Object();
	
	public HardwareMap() { 
		
		this.allDeviceMappings = new ArrayList<DeviceMapping<? extends HardwareDevice>>(30); // 30 is approximate
		
		this.allDeviceMappings.add(this.servoController);
		this.allDeviceMappings.add(this.crservo);	
	}
	
	// HardwareMap::remove
	public boolean remove(SerialNumber serialNumber, String deviceName, HardwareDevice device) {
		synchronized(lock){
			deviceName =deviceName.trim();
			List<HardwareDevice> list = allDevicesMap.get(deviceName);
			if(list != null){
				list.remove(device);
				if (list.isEmpty()){
					allDevicesMap.remove(deviceName);
				}
				allDevicesList = null;
				deviceName = null;
				if(serialNumber != null){
					serialNumberMap.remove(serialNumber);
				}
				return true;
		}
		return false;
		}
	}
	
	// HardwareMap::put
	public void put(String deviceName, HardwareDevice device) {
		internalPut(null, deviceName, device);
	} // put
	
	// HardwareMap::internalPut
	protected void internalPut(SerialNumber serialNumber, String deviceName, HardwareDevice device) {
		synchronized (lock) {
			deviceName = deviceName.trim();
			List<HardwareDevice> list = allDevicesMap.get(deviceName);
			if ( list == null ) {
				list = new ArrayList<HardwareDevice>(1);
				allDevicesMap.put(deviceName, list);
			}
			if (!list.contains(device)) {
				allDevicesList = null;
				list.add(device);
			}
			if (serialNumber != null) {
				serialNumberMap.put(serialNumber, device);
			}
			rebuildDeviceNamesIfNecessary();
			recordDeviceName(deviceName, device);
			
		} // sync lock
	} //internalPut
	
	// HardwareMap::buildAllDevicesList
	private void buildAllDevicesList() {
		if (allDevicesList == null) {
			Set<HardwareDevice> set = new HashSet<HardwareDevice>();
			for (String key : allDevicesMap.keySet() ) {
				set.addAll(allDevicesMap.get(key));
			}
			allDevicesList = new ArrayList<HardwareDevice>(set);
		}	
	} // buildAllDevicesList
	
	// HardwareMap::size
	public int size() {
		synchronized (lock) {
			buildAllDevicesList();
			return allDevicesList.size();
		}
	} // size
	
	// HardwareMap::Iterator
	@Override
	public @NonNull Iterator<HardwareDevice> iterator() {
		synchronized (lock) {
			buildAllDevicesList();
			return new ArrayList<>(allDevicesList).iterator(); // make copy for locking reasons
		}
	} // iterator
	
	// HardwareMap::recordDeviceName
	protected void recordDeviceName(String deviceName,HardwareDevice device) {
		deviceName = deviceName.trim();
		Set<String> names = this.deviceNames.get(device);
		if(names==null){
			names=new HashSet<String>();
			this.deviceNames.put(device,names);
		}
		names.add(deviceName);
	} // recordDeviceName
	
	// HardwareMap::rebuildDeviceNamesIfNecessary
	protected void rebuildDeviceNamesIfNecessary(){
		if (this.deviceNames == null){
			this.deviceNames = new ConcurrentHashMap<HardwareDevice, Set<String>>();
			for(Map.Entry<String, List<HardwareDevice>> pair:allDevicesMap.entrySet()){
				for(HardwareDevice device:pair.getValue()){
					recordDeviceName(pair.getKey(),device);
				}
			}
		}
	} // rebuildDeviceNamesIfNecessary
	
	

/******************************************************************************
 *  Begin sub-class implementations
 *****************************************************************************/

	/*  A  DeviceMapping  contains  a  subcollection  of  the  devices  registered  in  a  {@link  HardwareMap}
	 *  comprised  of  all  the  devices  of  a  particular  device  type
	 *
	 *  @param  <DEVICE_TYPE>
	 *  @see  com.qualcomm.robotcore.hardware.HardwareMap.DeviceMapping#get(String)
	 *  @see  #get(String)
	 */ 
	public class DeviceMapping<DEVICE_TYPE  extends  HardwareDevice>  implements  Iterable<DEVICE_TYPE>  {
	        
			private  Map  <String,  DEVICE_TYPE>  map  =  new  HashMap<String,  DEVICE_TYPE>();
	        private  Class<DEVICE_TYPE>  deviceTypeClass;
	        // protected final Object lock = new Object();

	        // DeviceMapping::DeviceMapping
	        public  DeviceMapping(Class<DEVICE_TYPE>  deviceTypeClass)  {
	            this.deviceTypeClass  =  deviceTypeClass;
	        }

	        // DeviceMapping::getDeviceTypeClass
	        //**  Returns  the  runtime  device  type  for  this  mapping  * 
	        public  Class<DEVICE_TYPE>  getDeviceTypeClass()  {
	            return  this.deviceTypeClass;
	        }

	        // DeviceMapping::cast
	        //**  A  small  utility  that  assists  in  keeping  the  Java  generics  type  system  happy  * 
	        public  DEVICE_TYPE  cast(Object  obj)  {
	            return  this.deviceTypeClass.cast(obj);
	        }

	        // DeviceMapping::get
	        public  DEVICE_TYPE  get(String  deviceName)  {
	        	
	        	System.out.println(" ## IM GONNA RUN THE DEVICE_TYPE FUNCTION! " + deviceName);
	        	
	            synchronized  (lock)  {
	                deviceName  =  deviceName.trim();
	                DEVICE_TYPE  device  =  map.get(deviceName);
	                
	                System.out.println(" ## map.get.deviceName " + map.get(deviceName));
	                System.out.println(" ## device "+ device );
	           
	                System.out.println(" ## device name gets trimmed " + deviceName);
	                
	                if  (device  ==  null)  {
	                    String  msg  =  String.format(" !! Unable  to  find  a  hardware  device  with  the  name  \"%s\"",  deviceName);
	                    throw  new  IllegalArgumentException(msg);
	                }
	                return  device;
	            }
	        }

	         /**
	          * // DeviceMapping::put
	          *  Registers  a  new  device  in  this  DeviceMapping  under  the  indicated  name.  Any  existing  device
	          *  with  this  name  in  this  DeviceMapping  is  removed.  The  new  device  is  also  added  to  the
	          *  overall  collection  in  the  overall  map  itself.  Note  that  this  method  is  normally  called
	          *  only  by  code  in  the  SDK  itself,  not  by  user  code.
	          *
	          *    deviceName    the  name  by  which  the  new  device  is  to  be  known  (case  sensitive)
	          *  @param  device            the  new  device  to  be  named
	          *  @see  HardwareMap#put(String,  HardwareDevice)
	          */ 
	        public  void  put(String  deviceName,  DEVICE_TYPE  device)  {
	            internalPut(null,  deviceName,  device);
	        }

	         /**
	          * // DeviceMapping::put
	          *  (Advanced)  Registers  a  new  device  in  this  DeviceMapping  under  the  indicated  name.  Any  existing  device
	          *  with  this  name  in  this  DeviceMapping  is  removed.  The  new  device  is  also  added  to  the
	          *  overall  collection  in  the  overall  map  itself.  Note  that  this  method  is  normally  called
	          *  only  by  code  in  the  SDK  itself,  not  by  user  code.
	          *
	          *  @param  serialNumber  the  serial  number  of  the  device
	          *  @param  deviceName    the  name  by  which  the  new  device  is  to  be  known  (case  sensitive)
	          *  @param  device            the  new  device  to  be  named
	          *  @see  HardwareMap#put(String,  HardwareDevice)
	          */ 
	        public  void  put(@NonNull  SerialNumber  serialNumber,  String  deviceName,  DEVICE_TYPE  device)  {
	            internalPut(serialNumber,  deviceName,  device);
	        }

	        // DeviceMapping::internalPut
	        protected  void  internalPut(SerialNumber  serialNumber,  String  deviceName,  DEVICE_TYPE  device)  {
	            synchronized  (lock)  {
	                // remove  whitespace  at   start  &  end
	                deviceName  =  deviceName.trim();

	                // Remove  any  existing  device  with  that  name
	                remove(serialNumber,  deviceName);

	                // Remember  the  new  device  in  the  overall  list
	                HardwareMap.this.internalPut(serialNumber,  deviceName,  device);

	                // Remember  the  new  device  here  locally,  too
	                putLocal(deviceName,  device);
	            }
	        }

	        // DeviceMapping::putLocal
	        public  void  putLocal(String  deviceName,  DEVICE_TYPE  device)  {
	            synchronized  (lock)  {
	                deviceName  =  deviceName.trim();
	                map.put(deviceName,  device);
	            }
	        }

	         /**
	          * // DeviceMapping::contains
	          *  Returns  whether  a  device  of  the  indicated  name  is  contained  within  this  mapping
	          *  @param  deviceName  the  name  sought
	          *  @return  whether  a  device  of  the  indicated  name  is  contained  within  this  mapping
	          */ 
	        public  boolean  contains(String  deviceName)  {
	            synchronized  (lock)  {
	                deviceName  =  deviceName.trim();
	                return  map.containsKey(deviceName);
	            }
	        }

	         /**
	          * // DeviceMapping::remove
	          *  (Advanced)  Removes  the  device  with  the  indicated  name  (if  any)  from  this  DeviceMapping.  The  device
	          *  is  also  removed  under  that  name  in  the  overall  map  itself.  Note  that  this  method  is  normally
	          *  called  only  by  code  in  the  SDK  itself,  not  by  user  code.
	          *
	          *  @param  deviceName    the  name  of  the  device  to  remove.
	          *  @return                        whether  any  modifications  were  made  to  this  DeviceMapping
	          *  @see  HardwareMap#remove
	          */ 
	          public  boolean  remove(String  deviceName)  {
	                return  remove(null,  deviceName);
	          }
	          
	         /**
	          * // DeviceMapping::remove
	          *  (Advanced)  Removes  the  device  with  the  indicated  name  (if  any)  from  this  DeviceMapping.  The  device
	          *  is  also  removed  under  that  name  in  the  overall  map  itself.  Note  that  this  method  is  normally
	          *  called  only  by  code  in  the  SDK  itself,  not  by  user  code.
	          *
	          *  @param  serialNumber  (optional)  the  serial  number  of  the  device  to  remove
	          *  @param  deviceName    the  name  of  the  device  to  remove.
	          *  @return                        whether  any  modifications  were  made  to  this  DeviceMapping
	          *  @see  HardwareMap#remove
	          */ 
	          
	          public  boolean  remove(SerialNumber  serialNumber,  String  deviceName)  {
	            synchronized  (lock)  {
	                deviceName  =  deviceName.trim();
	                HardwareDevice  device  =  map.remove(deviceName);
	                if  (device  !=  null)  {
	                    HardwareMap.this.remove(serialNumber,  deviceName,  device);
	                    return  true;
	                }
	                return  false;
	            }
	        }

	         /**
	          * // DeviceMapping::Iterator
	          *  Returns  an  iterator  over  all  the  devices  in  this  DeviceMapping.
	          *  @return  an  iterator  over  all  the  devices  in  this  DeviceMapping.
	          */ 
	        @Override  public  @NonNull  Iterator<DEVICE_TYPE>  iterator()  {
	            synchronized  (lock)  {
	                return  new  ArrayList<>(map.values()).iterator();
	            }
	        }

	         /**
	          * // DeviceMapping::Set
	          *  Returns  a  collection  of  all  the  (name,  device)  pairs  in  this  DeviceMapping.
	          *  @return  a  collection  of  all  the  (name,  device)  pairs  in  this  DeviceMapping.
	          */ 
	        public  Set<Map.Entry<String,  DEVICE_TYPE>>  entrySet()  {
	            synchronized  (lock)  {
	                return  new  HashSet<>(map.entrySet());
	            }
	        } // entrySet

	         /**
	          * // DeviceMapping::size
	          *  Returns  the  number  of  devices  currently  in  this  DeviceMapping
	          *  @return  the  number  of  devices  currently  in  this  DeviceMapping
	          */ 
	        public  int  size()  {
	            synchronized  (lock)  {
	                return  map.size();
	            }
	        } // size
	    } // DeviceMapping
	
} // class HardwareMap