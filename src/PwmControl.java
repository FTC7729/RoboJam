public interface PwmControl
    {
    /**
     * Sets the PWM range limits for the servo
     * @param range the new PWM range limits for the servo
     * @see #getPwmRange()
     */
    void setPwmRange(PwmRange range);

    /**
     * Returns the current PWM range limits for the servo
     * @return the current PWM range limits for the servo
     * @see #setPwmRange(PwmRange)
     */
    PwmRange getPwmRange();

    /**
     * Individually energizes the PWM for this particular servo.
     * @see #setPwmDisable()
     * @see #isPwmEnabled()
     */
    void setPwmEnable();

    /**
     * Individually denergizes the PWM for this particular servo
     * @see #setPwmEnable()
     */
    void setPwmDisable();

    /**
     * Returns whether the PWM is energized for this particular servo
     * @see #setPwmEnable()
     */
    boolean isPwmEnabled();

    /**
     * PwmRange instances are used to specify the upper and lower pulse widths
     * and overall framing rate for a servo.
     *
     * @see <a href="http://www.endurance-rc.com/ppmtut.php">Guide to PWM and PPM</a>
     * @see <a href="https://www.servocity.com/html/hs-485hb_servo.html">HS-485HB servo information</a>
     */
    class PwmRange
        {

        /** usFrameDefault is the default frame rate used, in microseconds */
        public static final double usFrameDefault = 20000;
        public static final double usPulseUpperDefault = 2400;
        public static final double usPulseLowerDefault = 600;

        /** defaultRange is the default PWM range used */
        public final static PwmRange defaultRange = new PwmRange(usPulseLowerDefault, usPulseUpperDefault);

        /** usPulseLower is the minimum PWM rate used, in microseconds. This corresponds to a servo position of 0.0. */
        public final double usPulseLower;
        /** usPulseLower is the maximum PWM rate used, in microseconds. This corresponds to a servo position of 1.0. */
        public final double usPulseUpper;
        /** usFrame is the rate, in microseconds, at which the PWM is transmitted. */
        public final double usFrame;

        /**
         * Creates a new PwmRange with the indicated lower and upper bounds and the default
         * framing rate.
         * @param usPulseLower the minimum PWM rate used, in microsecond
         * @param usPulseUpper the maximum PWM rate used, in microseconds
         */
        public PwmRange(double usPulseLower, double usPulseUpper)
            {
            this(usPulseLower, usPulseUpper, usFrameDefault);
            }

        /**
         * Creates a new PwmRange with the indicated lower and upper bounds and the specified
         * framing rate.
         * @param usPulseLower the minimum PWM rate used, in microsecond
         * @param usPulseUpper the maximum PWM rate used, in microseconds
         * @param usFrame the framing rate, in microseconds
         */
        public PwmRange(double usPulseLower, double usPulseUpper, double usFrame)
            {
            this.usPulseLower = usPulseLower;
            this.usPulseUpper = usPulseUpper;
            this.usFrame = usFrame;
            }

        @Override
        public boolean equals(Object o)
            {
            if (o instanceof PwmRange)
                {
                PwmRange him = (PwmRange)o;
                return this.usPulseLower==him.usPulseLower
                        && this.usPulseUpper==him.usPulseUpper
                        && this.usFrame==him.usFrame;
                }
            return false;
            }

        @Override
        public int hashCode()
            {
            return ((Double)this.usPulseLower).hashCode()
                ^ ((Double)this.usPulseUpper).hashCode()
                ^ ((Double)this.usFrame).hashCode();
            }
        }
    }