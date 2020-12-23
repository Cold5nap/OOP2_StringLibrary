package user.Cold5nap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestStringLibraryJUnit {

    private static final char[] CHARS =
            {'ш', 'е', 'л', ' ', 'к', 'о', 'т', 'и', 'к'};

    private static final StringLibrary STR = new StringLibrary(CHARS);


    @Test

    public void isEmpty() {
        Assertions.assertFalse(STR.isEmpty());

        StringLibrary str1 = new StringLibrary(null);
        Assertions.assertTrue(str1.isEmpty());
        char[] chars2 = {};
        StringLibrary str2 = new StringLibrary(chars2);
        Assertions.assertTrue(str2.isEmpty());
    }

    @Test
    public void indexOf() {
        Assertions.assertEquals(2, STR.indexOf('л'));

        Assertions.assertEquals(-1, STR.indexOf('!'));
    }

    @Test
    public void indexOf_fromIndex() {
        StringLibrary str0 = new StringLibrary(CHARS);
        char[] chars3 = new char[]{};
        Assertions.assertEquals(-1,str0.indexOf('А'));
        Assertions.assertEquals(str0.length()-1,str0.indexOf('к',5));
        Assertions.assertEquals(4,str0.indexOf('к',0));
    }

    @Test
    public void indexOfChars() {
        StringLibrary str0 = new StringLibrary(CHARS);
        char[] chars1 = new char[]{'А'};
        char[] chars2 = new char[]{'к', 'о', 'т', 'и', 'к'};
        char[] chars3 = new char[]{};
        Assertions.assertEquals(-1,str0.indexOf(chars1));
        Assertions.assertEquals(4,str0.indexOf(chars2));
        Assertions.assertEquals(-1,str0.indexOf(chars3));
    }

    @Test
    public void indexOfChars_fromIndex() {
        StringLibrary str0 = new StringLibrary(CHARS);
        char[] chars1 = new char[]{'А'};
        char[] chars2 = new char[]{'к','о'};
        char[] chars3 = new char[]{};
        Assertions.assertEquals(-1,str0.indexOf(chars1,0));
        Assertions.assertEquals(4,str0.indexOf(chars2,4));
        Assertions.assertEquals(-1,str0.indexOf(chars2,5));
        Assertions.assertEquals(-1,str0.indexOf(chars3,0));
    }

    @Test
    public void lastIndexOf() {
        Assertions.assertEquals(CHARS.length - 1, STR.lastIndexOf('к'));

        Assertions.assertEquals(-1, STR.lastIndexOf('!'));
    }

    @Test
    public void length() {
        Assertions.assertEquals(CHARS.length, STR.length());

        StringLibrary str1 = new StringLibrary(null);
        Assertions.assertEquals(0, str1.length());
    }

    @Test
    public void getChars() {
        char[] chars = new char[2];
        STR.getChars(2, 4, chars,0);
        Assertions.assertEquals('л',chars[0]);
        Assertions.assertEquals(' ',chars[1]);

        char[] chars1 = new char[2];
        STR.getChars(2, 5, chars1,0);
        Assertions.assertEquals('л',chars1[0]);
        Assertions.assertEquals(' ',chars1[1]);

        char[] chars2 = {'А','А','А'};
        STR.getChars(2, 5, chars2,1);
        Assertions.assertEquals(chars2[0],'А');
        Assertions.assertEquals(chars2[1],'л');
        Assertions.assertEquals(chars2[2],' ');
    }
    @Test
    public void concat() {
        StringLibrary str = new StringLibrary(CHARS);
        StringLibrary newStr = new StringLibrary(new char[]{'A','A'});
        str.concat(newStr);
        //Assertions.assertEquals(str.length() - 2, str.lastIndexOf('A'));
        Assertions.assertEquals(str.length() - 1, str.lastIndexOf('A'));
    }

    @Test
    public void compareTo() {
        StringLibrary str = new StringLibrary(CHARS);
        StringLibrary newStr = new StringLibrary(new char[]{'A','A'});
        StringLibrary newStr1 = new StringLibrary(
                new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});
        StringLibrary newStr2 = new StringLibrary(
                new char[]{'ш', 'е', 'л', ' ', 'к', 'о', 'т', 'и', 'к'});

        Assertions.assertEquals(-1,str.compareTo(newStr));
        Assertions.assertEquals(-1,str.compareTo(newStr1));
        Assertions.assertEquals(1,str.compareTo(newStr2));
    }

    @Test
    public void charAt() {
        StringLibrary str = new StringLibrary(CHARS);

        Assertions.assertEquals('е',str.charAt(1));
    }

    @Test
    public void codePointAt() {
        StringLibrary str = new StringLibrary(CHARS);

        Assertions.assertEquals(1077,str.charAt(1));
    }

    @Test
    public void codePointBefore() {
        StringLibrary str = new StringLibrary(CHARS);

        Assertions.assertEquals(1077,str.codePointBefore(2));
    }

    @Test
    public void codePointCount() {
        StringLibrary str = new StringLibrary(CHARS);

        Assertions.assertEquals(1077,str.codePointCount(1,1));
    }

    @Test
    public void replace() {
        StringLibrary str = new StringLibrary(CHARS);
        str.replace('к','А');
        Assertions.assertEquals('А',str.charAt(4));
    }

    @Test
    public void replaceChars() {
        StringLibrary str = new StringLibrary(CHARS);
        char[] chars1 = new char[]{'А','А','А'};
        char[] chars2 = new char[]{'к'};
        str.replace(chars2,chars1);
        Assertions.assertEquals(4,str.indexOf('А'));
        Assertions.assertEquals(6,str.lastIndexOf('А'));
    }

    @Test
    public void equals() {
        StringLibrary str = new StringLibrary(CHARS);
        char[] chars1 = new char[]{'А','А','А'};
        char[] chars2 = new char[]{'ш', 'е', 'л', ' ', 'к', 'о', 'т', 'и', 'к'};
        StringLibrary str1 = new StringLibrary(chars1);
        StringLibrary str2 = new StringLibrary(chars2);
        Assertions.assertFalse(str.equals(str1));
        Assertions.assertFalse(str.equals(null));
        Assertions.assertTrue(str.equals(str2));
    }
    @Test
    public int hashCode() {
        StringLibrary str = new StringLibrary(CHARS);
        char[] chars1 = new char[]{'А','А','А'};
        char[] chars2 = new char[]{'ш', 'е', 'л', ' ', 'к', 'о', 'т', 'и', 'к'};
        StringLibrary str1 = new StringLibrary(chars1);
        StringLibrary str2 = new StringLibrary(chars2);
        Assertions.assertNotEquals(str1.hashCode(),str.hashCode());
        Assertions.assertNotEquals(str2.hashCode(),str.hashCode());
        Assertions.assertEquals(str.hashCode(),str.hashCode());
        return 0;
    }

    @Test
    public void getBytes() {
        StringLibrary str = new StringLibrary(CHARS);
        Assertions.assertTrue(
                str.compareTo(StringLibrary.getString(str.getBytes())) > 0);

    }

    @Test
    public void split() {
        StringLibrary str = new StringLibrary(CHARS);
        char[] chars1 = new char[]{'к'};
        char[] chars2 = new char[]{'к', 'о', 'т'};

        StringLibrary str1 = new StringLibrary(chars1);
        StringLibrary str2 = new StringLibrary(chars2);
        StringLibrary[] strs1 = str.split(str1);
        StringLibrary[] strs2 = str.split(str2);
        StringLibrary str11 = new StringLibrary(new char[]{'ш','е','л',' '});
        StringLibrary str12 = new StringLibrary(new char[]{'о','т','и'});
        StringLibrary str21 = new StringLibrary(new char[]{'ш','е','л',' '});
        StringLibrary str22 = new StringLibrary(new char[]{'и','к'});
        Assertions.assertTrue(strs1[0].compareTo(str11)>0);
        Assertions.assertTrue(strs1[1].compareTo(str12)>0);
        Assertions.assertTrue(strs2[0].compareTo(str21)>0);
        Assertions.assertTrue(strs2[1].compareTo(str22)>0);
    }

    @Test
    public void toUpperCase() {
        StringLibrary str = new StringLibrary(CHARS);
        StringLibrary str1 = new StringLibrary(
                new char[]{'Ш', 'Е', 'Л', ' ', 'К', 'О', 'Т', 'И', 'К'});
        Assertions.assertTrue(str1.compareTo(str.toUpperCase())>0);

    }

    @Test
    public void substring() {
        StringLibrary str = new StringLibrary(CHARS);
        StringLibrary str1 = new StringLibrary(
                new char[]{ 'л', ' ', 'к', 'о', 'т', 'и', 'к'});
        Assertions.assertTrue(str.substring(2).equals(str1));
    }

    @Test
    public void trim() {
        StringLibrary str1 = new StringLibrary(
                new char[]{ ' ',' ','л', ' ', 'к', 'о', 'т', 'и', 'к',' ',' ',' '});
        Assertions.assertEquals(7,str1.trim().length());
    }



}
