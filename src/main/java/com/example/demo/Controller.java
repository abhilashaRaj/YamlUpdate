package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
@RestController
public class Controller {

	@Autowired

	private CreateYamlFile createYamlFile;
	
	
	@GetMapping(value="/yaml")
	public Map<String, Object> Yaml() throws IOException
	{
		return createYamlFile.yaml();
		//return createYamlFile.yaml();
	}
	@GetMapping(value="/updateYaml")
	public Map<String, Object> YamlUpdate() throws IOException
	{
	  return createYamlFile.yamlUpdate();
	}
	@GetMapping(value="/write")
	public JsonNode yamlWrite() throws IOException
	{
		return createYamlFile.yamlWrite();
    }
	@PostMapping(value = "/update")
	public ResponseEntity<String> updateToYaml(@RequestBody String json, @RequestParam String file) {
	  try {
	    // Serialize JSON node to YAML
	    Yaml yaml = new Yaml();

       // convert json into yaml
	    ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode  = objectMapper.readTree(json);
        // create a YAMLMapper object
        YAMLMapper yamlMapper = new YAMLMapper();
        yamlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // convert JsonNode to YAML
        String yamlStr = yamlMapper.writeValueAsString(jsonNode);
	    //String yamlStr1 = yaml.dump(yamlStr);

	    // Write YAML to file
        
     // String Location = file.getFile();
        
	    FileWriter writer = new FileWriter(file);
	   // String output = yamlStr.toString();
	    writer.write(yamlStr);
	    writer.close();

	    return ResponseEntity.ok("JSON node written to YAML file");
	  } catch (IOException e) {
	    e.printStackTrace();
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error writing YAML file");
	  }
}
//	@PostMapping("/dynamic")
//	public ResponseEntity<String> dynamicToYaml(@RequestBody String json ) {
//	  try {
//	    // Serialize JSON node to YAML
//	   // Yaml yaml = new Yaml();
//
//       // convert json into yaml
//	    ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode  = objectMapper.readTree(json);
//        // create a YAMLMapper object
//        YAMLMapper yamlMapper = new YAMLMapper();
//        yamlMapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//        // convert JsonNode to YAML
//        String yamlStr = yamlMapper.writeValueAsString(jsonNode);
//	    //String yamlStr1 = yaml.dump(yamlStr);
//
//	    // Write YAML to file
//	   // FileWriter writer = new FileWriter("D:\\ITM JAVA\\itm_java_dev\\testSnakeYaml\\src\\main\\resources\\example4.yaml");
//        FileWriter writer = new FileWriter("D:\\example5.yaml");
//	   // String output = yamlStr.toString();
//	    writer.write(yamlStr);
//	    writer.close();
//
//	    return ResponseEntity.ok("JSON node written to YAML file");
//	  } catch (IOException e) {
//	    e.printStackTrace();
//	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error writing YAML file");
//	  }
//	  
//}
	



}
