package me.GeekCodePlus.utils;

import com.google.common.base.Joiner;
import me.GeekCodePlus.Configure.ConfigManage;
import org.apache.commons.lang.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RandomCode {


    private final List<String> strArr = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    public String getRandomGeekA() {
        String A = Randoms(4);
        String B = Randoms(4);
        String C = Randoms(4);
        String D = Randoms(4);
        return "GeekA-" + A + "-" + B + "-" + C + "-" + D;
    }

    public String getRandomGeekI() {
        int intNum = 5;
        Random rm = new Random();
        double number = (1 + rm.nextDouble()) * Math.pow(4, intNum);
        String random_out = RandomStringUtils.randomAlphanumeric(4);
        String random = RandomStringUtils.randomAlphanumeric(4);
        return "GeekI-" + random + "-" + String.valueOf(number).substring(1, intNum + 1).replace(".", "") + "-" + random_out;
    }

    public String getRandomGeekR() {
        int num = ConfigManage.CODE_LENGTH;
        return "GeekS-" + RandomStringUtils.randomAlphanumeric(num);
    }

    private String Randoms(int integer) {
        Random r = new Random();
        List<String> aa_list = new ArrayList<>();
        for (int i = 0; integer > i; i++) {
            String out = strArr.get(r.nextInt(26));
            aa_list.add(out);
        }
        return Joiner.on("").join(aa_list);
    }
}
