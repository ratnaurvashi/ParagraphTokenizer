package com.stackroute;

import java.util.*;
import java.lang.*;
import java.io.*;
import org.json.simple.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/* Name of the class has to be "Main" only if the class is public. */
public class ParagraphTokenizer
{
    public static void main (String[] args) throws java.lang.Exception
    {

        String text= "This is a small demonstration .pdf file -\n" +
                "\n" +
                "just for use in the Virtual Mechanics tutorials. More text. And more\n" +
                "text. And more text. And more text. And more text.\n" +
                "\n" +
                "\n" +
                "And more text. And more text. And more text. And more text. And more\n" +
                "text. And more text. Boring, zzzzz. And more text. And more text. And\n" +
                "more text. And more text. And more text. And more text. And more text.And more text. And more text! \n" +
                "\n" +
                "\n" +
                "And more text. And more text. And more text. And more text. And more\n" +
                "text. And more text. And more text. Even more. Continued on page 2 ... \n";

        String patternStr = "[.?!][/\\s/g]?\\n+";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(text);
        String[] para= text.split(patternStr);
        int i=0;
        while (matcher.find()) {
            String paragraph = matcher.group();
            para[i]=para[i]+paragraph.charAt(0);
            i++;
        }
        int cnt=0;
        int id=1;
        JSONObject obj = new JSONObject();
        while (cnt<para.length) {
            obj.put("paragraphId",id);
//            System.out.println("paraId: "+id);
            id++;
            obj.put("paragraphText",para[cnt]);
//            System.out.println(para[cnt]);
            cnt++;

            StringWriter out = new StringWriter();
            obj.writeJSONString(out);
//
//            String jsonText = out.toString();
//            System.out.print(jsonText);
            System.out.println(obj);
        }
    }
}