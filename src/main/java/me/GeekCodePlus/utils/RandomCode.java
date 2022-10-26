package me.GeekCodePlus.utils;

import com.google.common.base.Joiner;
import me.GeekCodePlus.Configure.ConfigManage;
import org.apache.commons.lang.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RandomCode {


    private final List<String> strArr = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    public String getRandomGeekA() {
        String head = "GeekA";
        String A = Random_A();
        String B = Random_B();
        String C = Random_C();
        String D = Random_D();
        return head+"-"+A+"-"+B+"-"+C+"-"+D;
    }
    public String getRandomGeekI() {
        String name = "GeekI";
        int intnum = 5;
        //
        Random rm = new Random();
        double number = (1 + rm.nextDouble()) * Math.pow(4, intnum);
        String number_out = String.valueOf(number);
        String random_out = RandomStringUtils.randomAlphanumeric(4);
        String random = RandomStringUtils.randomAlphanumeric(4);
        return name + "-" + random +"-"+ number_out.substring(1, intnum + 1).replace(".","") +"-"+ random_out;
    }
    public String getRandomGeekR() {
        int num = ConfigManage.CODE_LENGTH;
        return "GeekS-"+RandomStringUtils.randomAlphanumeric(num);
    }

    private String Random_A() {
        int aa = 4;
        Random r = new Random();
        List<String> aa_list = new ArrayList<>();
        for ( int AA = 0; aa > AA; AA++){
            String out = strArr.get(r.nextInt(26)).replace("[","").replace("]","");
            aa_list.add(out);
        }
        return Joiner.on("").join(aa_list).replace("[","").replace("]","");
    }
    private String Random_B() {
        int bb = 4;
        Random r = new Random();
        List<String> bb_list = new ArrayList<>();
        for ( int BB = 0; bb > BB; BB++){
            String out = strArr.get(r.nextInt(26)).replace("[","").replace("]","");
            bb_list.add(out);
        }
        return Joiner.on("").join(bb_list).replace("[","").replace("]","");
    }
    private String Random_C() {
        int cc = 4;
        Random r = new Random();
        List<String> cc_list = new ArrayList<>();
        for ( int CC = 0; cc > CC; CC++){
            String out = strArr.get(r.nextInt(26)).replace("[","").replace("]","");
            cc_list.add(out);
        }
        return Joiner.on("").join(cc_list).replace("[","").replace("]","");
    }
    private String Random_D() {
        int dd = 4;
        Random r = new Random();
        List<String> dd_list = new ArrayList<>();
        for ( int DD = 0; dd > DD; DD++){
            String out = strArr.get(r.nextInt(26)).replace("[","").replace("]","");
            dd_list.add(out);
        }
        return Joiner.on("").join(dd_list).replace("[","").replace("]","");
    }
}
