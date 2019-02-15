package com.stackroute;
import com.stackroute.domain.Paragraph;
import java.util.*;
import java.lang.*;
import java.io.*;
import org.json.simple.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.stackroute.domain.Paragraph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class ParagraphTokenizerApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ParagraphTokenizerApplication.class, args);


		String text = "This is a small demonstration .pdf file -\n" +
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

		Paragraph paraObj = new Paragraph();
		String patternStr = "[.?!][/\\s/g]?\\n+";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(text);
		String[] para = text.split(patternStr);
		int i = 0;
		while (matcher.find()) {
			String paragraph = matcher.group();
			para[i] = para[i] + paragraph.charAt(0);
			i++;
		}
		int cnt = 0;
		paraObj.setParagraphId(1);
		int id = paraObj.getParagraphId();
		JSONObject obj = new JSONObject();
		while (cnt < para.length) {
			obj.put("paragraphId", id);
			paraObj.setParagraphId(id++);
			paraObj.setParagraphText(para[cnt]);
			obj.put("paragraphText", paraObj.getParagraphText());
			cnt++;

			StringWriter out = new StringWriter();
			try {
				obj.writeJSONString(out);
			} catch (IOException e) {
				e.printStackTrace();
			}

//            String jsonText = out.toString();
//            System.out.print(jsonText);
			System.out.println(obj);
		}
	}
}