package user.Cold5nap;

import java.util.Arrays;

public class StringLibrary implements Comparable {
    private char[] string;

    public StringLibrary(char[] string) {
        if (string != null) {
            this.string = new char[string.length];
            System.arraycopy(string, 0, this.string, 0, string.length);
        } else
            this.string = new char[]{};

    }

    public static byte[] getBytes(StringLibrary str) {
        char[] buffer = str.getChars();
        byte[] b = new byte[buffer.length << 1];
        for (int i = 0; i < buffer.length; i++) {
            int bpos = i << 1;
            b[bpos] = (byte) ((buffer[i] & 0xFF00) >> 8);
            b[bpos + 1] = (byte) (buffer[i] & 0x00FF);
        }
        return b;
    }

    public static StringLibrary getString(byte[] bytes) {
        char[] buffer = new char[bytes.length >> 1];
        for (int i = 0; i < buffer.length; i++) {
            int bpos = i << 1;
            char c = (char) (((bytes[bpos] & 0x00FF) << 8) + (bytes[bpos + 1] & 0x00FF));
            buffer[i] = c;
        }
        return new StringLibrary(buffer);
    }

    public char[] getChars() {
        return string;
    }

    public char charAt(int index) {
        return string[index];
    }

    public int codePointAt(int index) {
        if (index > 0 && index < string.length)
            return Character.codePointAt(string, index);
        else
            return -1;
    }

    public int codePointBefore(int index) {
        if (index > 0 && index < string.length)
            return Character.codePointAt(string, index - 1);
        else
            return -1;
    }

    public int codePointCount(int beginIndex, int endIndex) {
        if (beginIndex > 0 && beginIndex < string.length
                && endIndex > 0 && endIndex < string.length) {
            int count = 0;
            if (beginIndex < endIndex) {
                for (int i = beginIndex; i < endIndex; i++) {
                    count += Character.codePointAt(string, i);
                }
            } else {
                for (int i = beginIndex; i >= endIndex; i--) {
                    count += Character.codePointAt(string, i);
                }
            }
            return count;
        }
        return -1;
    }

    public StringLibrary concat(StringLibrary str) {
        char[] result = new char[string.length + str.length()];
        System.arraycopy(string, 0, result, 0, string.length);
        str.getChars(0, str.length(), result, string.length);
        string = result;
        return this;
    }


    public byte[] getBytes() {
        byte[] b = new byte[string.length << 1];
        for (int i = 0; i < string.length; i++) {
            int bpos = i << 1;
            b[bpos] = (byte) ((string[i] & 0xFF00) >> 8);
            b[bpos + 1] = (byte) (string[i] & 0x00FF);
        }
        return b;
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        for (int i = dstBegin; i < dst.length && srcBegin < srcEnd; i++) {
            dst[i] = string[srcBegin];
            srcBegin++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringLibrary that = (StringLibrary) o;
        return Arrays.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(string);
    }

    public int indexOf(int ch) {
        if (isEmpty()) return -1;
        for (int i = 0; i < string.length; i++) {
            if (ch == string[i]) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(int ch, int fromIndex) {
        if (isEmpty()) return -1;
        for (int i = fromIndex; i < string.length; i++) {
            if (ch == string[i]) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(char[] str) {
        if (str == null || str.length == 0 || isEmpty()) return -1;
        int counter = 0;
        int lastIndexOrigin = 0;
        for (int i = 0; i < string.length && counter < str.length; i++) {
            if (str[counter] == string[i]) {
                counter++;
            } else {
                counter = 0;
            }
            lastIndexOrigin++;
        }
        if (counter == str.length)
            return lastIndexOrigin - counter;
        else return -1;

    }


    public int indexOf(char[] str, int fromIndex) {

        if (str == null || str.length == 0 || isEmpty()) return -1;
        int counter = 0;
        int lastIndexOrigin = fromIndex;
        for (int i = fromIndex; i < string.length && counter < str.length; i++) {
            if (str[counter] == string[i]) {
                counter++;
            } else {
                counter = 0;
            }
            lastIndexOrigin++;
        }
        if (counter == str.length)
            return lastIndexOrigin - counter;
        else return -1;

    }

    public boolean isEmpty() {
        return string == null || string.length == 0;
    }


    public int lastIndexOf(int ch) {
        if (isEmpty()) return -1;
        for (int i = string.length - 1; i >= 0; i--) {
            if (ch == string[i]) {
                return i;
            }
        }

        return -1;
    }

    public int length() {
        if (!isEmpty())
            return string.length;
        else return 0;
    }

    @Override
    public int compareTo(Object otherStringLibrary) {
        StringLibrary str = (StringLibrary) otherStringLibrary;
        if (str == null || str.length() != length()) return -1;
        char[] chars = str.getChars();
        for (int i = 0; i < string.length; i++) {
            if (chars[i] != string[i]) return -1;
        }
        return 1;
    }

    public void replace(char oldChar, char newChar) {
        string[indexOf(oldChar)] = newChar;
    }

    public void replace(char[] oldChars, char[] newChars) {
        int counter = 0;
        int lastIndexOrigin = 0;
        for (int i = 0; i < string.length && counter < oldChars.length; i++) {
            if (oldChars[counter] == string[i]) {
                counter++;
            } else {
                counter = 0;
            }
            lastIndexOrigin++;
        }

        int firstIndexOrigin = lastIndexOrigin - oldChars.length;
        char[] result = new char[string.length - oldChars.length + newChars.length];
        for (int i = 0; i < firstIndexOrigin; i++) {
            result[i] = string[i];
        }
        int b = 0;

        for (int i = firstIndexOrigin; i < firstIndexOrigin + newChars.length; i++) {
            result[i] = newChars[b];
            b++;
        }
        firstIndexOrigin += newChars.length;
        b = lastIndexOrigin;
        for (int i = firstIndexOrigin; i < result.length; i++) {
            result[i] = string[b];
            b++;
        }
        this.string = new char[result.length];
        System.arraycopy(result, 0, this.string, 0, string.length);

    }

    public StringLibrary[] split(StringLibrary regex) {
        if (regex.isEmpty()) return new StringLibrary[]{new StringLibrary(string)};

        char[] chars = regex.getChars();
        int index = 0;
        int counter = 0;
        int lastIndex = 0;
        while (index < string.length) {
            if (indexOf(chars, index) < 0) {
                counter++;
                break;
            } else
                index = indexOf(chars, index) + regex.length();
            counter++;
        }
        index = 0;
        StringLibrary[] strs = new StringLibrary[counter];
        for (int i = 0; i < strs.length; i++) {
            if (indexOf(chars, index) < 0) {
                index += regex.length() - 1;
                lastIndex = string.length;
            } else
                lastIndex = indexOf(chars, index);
            char[] chars1 = new char[Math.abs(lastIndex - index)];
            for (int j = 0; j < chars1.length; ) {
                chars1[j] = string[index];
                j++;
                index++;
            }
            strs[i] = new StringLibrary(chars1);
            index++;
        }
        return strs;
    }

    public StringLibrary substring(int beginIndex, int endIndex) {
        char[] chars = new char[endIndex - beginIndex];
        System.arraycopy(string, beginIndex, chars, 0, endIndex - beginIndex);
        return new StringLibrary(chars);
    }

    public StringLibrary substring(int beginIndex) {
        char[] chars = new char[length() - beginIndex];
        System.arraycopy(string, beginIndex, chars, 0, length() - beginIndex);
        return new StringLibrary(chars);
    }

    public StringLibrary toLowerCase() {
        char[] chars = new char[length()];
        System.arraycopy(string, 0, chars, 0, length());
        for (int i = 0; i < length(); i++) {
            chars[i] = Character.toLowerCase(chars[i]);
        }
        return new StringLibrary(chars);
    }

    public StringLibrary toUpperCase() {
        char[] chars = new char[length()];
        System.arraycopy(string, 0, chars, 0, length());
        for (int i = 0; i < length(); i++) {
            chars[i] = Character.toUpperCase(chars[i]);
        }
        return new StringLibrary(chars);
    }

    public StringLibrary trim() {
        int beginIndex = 0;
        int endIndex = string.length-1;
        while (string[beginIndex] == ' ') beginIndex++;
        while (string[endIndex] == ' ') endIndex--;
        char[] chars = new char[endIndex - beginIndex+1];
        System.arraycopy(string, beginIndex, chars, 0, endIndex - beginIndex+1);
        return new StringLibrary(chars);
    }
}
