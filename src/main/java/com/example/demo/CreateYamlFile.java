package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

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
	public Map<String, Object> yamlUpdate() throws IOException {
		 File file = new File("D:\\ITM JAVA\\itm_java_dev\\testSnakeYaml\\src\\main\\resources\\example.yaml");
	        Yaml yaml = new Yaml();
	        Map<String, Object> data = yaml.load(new FileInputStream(file));

	        Map<String,Object>data2=new HashMap<>();
	        data2.put("age", 21);
	        
	        // Modify the data
	        data.put("address", "Patna");
	        data.put("year", data2);

	        // Save the updated data to the YAML file
	        Writer writer = new FileWriter(file);
	        yaml.dump(data, writer);
	        writer.close();
			return data;
	    }
	public JsonNode yamlWrite() throws IOException{
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

	    // create JsonNode instance with initial data
	    JsonNode node = mapper.createObjectNode()
	        .put("name", "Jai")
	        .put("address", "Jaipur");

	    // create HashMap instance with additional data
	    HashMap<String, Object> map = new HashMap<>();
	    map.put("city", node);
	    map.put("country", "USA");

	    // add HashMap data to JsonNode
	    for (String key : map.keySet()) {
	      ((ObjectNode) node).put(key, String.valueOf(map.get(key)));
	    }

	    // write JsonNode as YAML to file
	    File file = new File("D:\\ITM JAVA\\itm_java_dev\\testSnakeYaml\\src\\main\\resources\\example.yaml");
	    mapper.writeValue(file, node);
		return node;
	  }
	
	}
	
	
	