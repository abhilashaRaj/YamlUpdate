package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

@Service
public class CreateYamlFile {

	public Map<String, Object> yaml() throws IOException {
		//read
		InputStream inputStream = new FileInputStream(new File("D:\\ITM JAVA\\itm_java_dev\\testSnakeYaml\\src\\main\\resources\\example.yaml"));

		Yaml yaml = new Yaml();
		Map<String, Object> data = yaml.load(inputStream);
		System.out.println(data);
		return data;
   }
	
}
	
	
	