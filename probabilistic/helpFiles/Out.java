import java.io.*;

/** Simple output to the console and to files.
 <p>Copyright (c) 2005 Hanspeter Moessenboeck, University of Linz</p>
 <p>Copyright (c) 2018 ETH Zurich</p>

 <p>This class is free software; you can redistribute it and/or modify it
 under the terms of the GNU General Public License as published by the
 Free Software Foundation; either version 2, or (at your option) any
 later version.</p>

 <p>This class is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 or FITNESS FOR A PARTICULAR PURPOSE.  See the <a href="http://www.gnu.org/copyleft/gpl.html">
 GNU General Public License</a> for more details.</p>
 <hr>

 <p>This class allows printing formatted data either to the console
 or to a file. It is intended to be used in an introductory
 programming course when classes, packages and exceptions are unknown
 at the beginning. To use it, simply copy Out.class into the
 current directory. </p>

 <p>All output goes to the current output file, which is initially
 the console. Opening a file with open() makes it the new current
 output file. Closing a file with close() switches back to the previous
 output file.</p>
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class Out {

    private static PrintStream out;
    private static PrintStream[] stack;
    private static int sp;
    private static boolean done;

    /** Return true if the previous Out operation was
     successful, otherwise return false. */
    public static boolean done() {
        return done && ! getOut().checkError();
    }

    /** Print the boolean value b either as "true" or "false". */
    public static void print(boolean b) { getOut().print(b); }

    /** Print the character value c. */
    public static void print(char s) { getOut().print(s); }

    /** Print the integer value i. */
    public static void print(int i) { getOut().print(i); }

    /** Print the long value l. */
    public static void print(long l) { getOut().print(l); }

    /** Print the float value f. */
    public static void print(float f) { getOut().print(f); }
    
    /** Print the float value f, rounded to `digits` decimal digits. */
    public static void print(float f, int digits) { getOut().printf("%." + digits + "f", f); }

    /** Print the double value d. */
    public static void print(double d) { getOut().print(d); }
    
    /** Print the double value d, rounded to `digits` decimal digits */
    public static void print(double d, int digits) { getOut().printf("%." + digits + "f", d); }

    /** Print the character array a. */
    public static void print(char[] a) { getOut().print(a); }

    /** Print the String s. */
    public static void print(String s) { getOut().print(s); }

    /** Print the Object o as resulting from String.valueOf(o). */
    public static void print(Object o) { getOut().print(o); }

    /** Terminate the current line by writing a line separator string.
     On windows this is the character sequence '\r' and '\n' */
    public static void println() { getOut().println(); }

    /** Print the boolean value b and terminate the line. */
    public static void println(boolean b) { getOut().println(b); }

    /** Print the character value c and terminate the line. */
    public static void println(char s) { getOut().println(s); }

    /** Print the integer value i and terminate the line. */
    public static void println(int i) { getOut().println(i); }

    /** Print the long value l and terminate the line. */
    public static void println(long l) { getOut().println(l); }

    /** Print the float value f and terminate the line. */
    public static void println(float f) { getOut().println(f); }
    
    /** Print the float value f, rounded to `digits` decimal digits, and terminate the line. */
    public static void println(float f, int digits) { getOut().printf("%." + digits + "f\n", f); }

    /** Print the double value d and terminate the line. */
    public static void println(double d) { getOut().println(d); }
    
    /** Print the double value d, rounded to `digits` decimal digits, and terminate the line. */
    public static void println(double d, int digits) { getOut().printf("%." + digits + "f\n", d); }

    /** Print the character array a and terminate the line. */
    public static void println(char[] a) { getOut().println(a); }

    /** Print the String s and terminate the line. */
    public static void println(String s) { getOut().println(s); }

    /** Print the Object o as resulting from String.valueOf(o)
     and terminate the line. */
    public static void println(Object o) { getOut().println(o); }

    /** Open the file with the name fn as the current output file.
     All subsequent output goes to this file until it is closed.
     The old output file will be restored when the new output file is closed. */
    @SuppressWarnings("SameParameterValue")
    public static void open(String fn) {
        try {
            PrintStream s = new PrintStream(new FileOutputStream(fn));
            stack[sp++] = getOut();
            out = s;
        } catch (Exception e) {
            done = false;
        }
    }

    /** Close the current output file.
     The previous output file is restored and becomes the current output file. */
    public static void close() {
        getOut().flush();
        getOut().close();
        if (sp > 0) out = stack[--sp];
    }

    private static PrintStream getOut(){
        if (sp == 0 && out != System.out){
            initialize();
        }
        return out;
    }

    private static void initialize() {
        out = System.out;
        done = true;
    }

    static { // initializer
        initialize();
        stack = new PrintStream[8];
        sp = 0;
    }

}
