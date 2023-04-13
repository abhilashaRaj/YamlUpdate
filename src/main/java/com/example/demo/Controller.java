package com.example.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
@RestController
public class Controller {

	@Autowired

	private CreateYamlFile createYamlFile;
	
	/**
	 * @apiNote reading the yaml file
	 * @return
	 * @throws IOException
	 */
	@GetMapping(value="/yaml")
	public Map<String, Object> Yaml() throws IOException
	{
		return createYamlFile.yaml();
		//return createYamlFile.yaml();
	}
	

	/**
	 * 
	 * @param filePath
	 * @return
	 */
	 @GetMapping("/read-yaml")
	    public Map<String, Object> readYamlFile(@RequestParam("path") String filePath) {
	        Map<String, Object> yamlData = null;
	        try {
	            // Load YAML file from given path
	            Yaml yaml = new Yaml();
	            yamlData = yaml.load(new FileInputStream(filePath));
	        } catch (FileNotFoundException e) {
	            // Handle file not found exception
	            e.printStackTrace();
	        }
	        return yamlData;
	    }
	/**
	 * @apiNote Converts a JSON and creates a YAML file
	 * @param json
	 * @param file
	 * @return
	 */
	
	
	@PostMapping(value = "/update")
	public ResponseEntity<String> updateToYaml(@RequestBody String json, @RequestParam String file) 
	{
	  try {
       // convert json into yaml
	    ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode  = objectMapper.readTree(json);
        // create a YAMLMapper object
        YAMLMapper yamlMapper = new YAMLMapper();
        yamlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // convert JsonNode to YAML
        String yamlStr = yamlMapper.writeValueAsString(jsonNode);
        
	    FileWriter writer = new FileWriter(file);
	    writer.write(yamlStr);
	    writer.close();

	    return ResponseEntity.ok("JSON node written to YAML file");
	  } 
	  catch (IOException e) 
	  {
	    e.printStackTrace();
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error writing YAML file");
	  }
    }
}
