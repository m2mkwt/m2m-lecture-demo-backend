package kr.co.m2m.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import kr.co.m2m.framework.exception.JsonException;

public final class JsonUtils {
  private static ObjectMapper MAPPER = createNewObjectMapper(true);
  private static ObjectMapper MAPPER_NON_EMPTY = createNewObjectMapper(false);

  public static ObjectMapper getObjectMapper() {
    return MAPPER;
  }

  public static ObjectMapper getObjectMapperNonEmpty() {
    return MAPPER_NON_EMPTY;
  }

  public static ObjectMapper createNewObjectMapper(boolean includeType) {
    ObjectMapper mapper = new ObjectMapper();
    if (includeType) {
      mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    } else {
      mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }
    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);

    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
    return mapper;
  }

  public static <T> T convertValue(Object object, Class<T> clazz) {
    try {
      if (object == null) {
        return null;
      }
      return (T) MAPPER.convertValue(object, clazz);
    } catch (IllegalArgumentException e) {
      throw new JsonException(String.format("Unable to read object '%s' from input '%s'",
          new Object[] {clazz.getSimpleName(), object}), e);
    }
  }

  public static <T> T readValue(byte[] object, Class<T> clazz) {
    try {
      if (object == null) {
        return null;
      }
      return (T) MAPPER.readValue(object, clazz);
    } catch (IllegalArgumentException e) {
      throw new JsonException(String.format("Unable to read object '%s' from input '%s'",
          new Object[] {clazz.getSimpleName(), object}), e);
    } catch (JsonParseException e) {
      throw new JsonException(String.format("Unable to read object '%s' from input '%s'",
          new Object[] {clazz.getSimpleName(), object}), e);
    } catch (JsonMappingException e) {
      throw new JsonException(String.format("Unable to read object '%s' from input '%s'",
          new Object[] {clazz.getSimpleName(), object}), e);
    } catch (IOException e) {
      throw new JsonException(String.format("Unable to read object '%s' from input '%s'",
          new Object[] {clazz.getSimpleName(), object}), e);
    }
  }

  public static <T> T readJson(String jsonString, Class<T> clazz) {
    try {
      return (T) MAPPER.readValue(jsonString, clazz);
    } catch (IOException e) {
      throw new JsonException(String.format("Unable to read JSON object '%s' from input '%s'",
          new Object[] {clazz.getSimpleName(), jsonString}), e);
    }
  }

  public static <T> T readJson(String jsonString, TypeReference<T> typeReference) {
    try {
      return (T) MAPPER.readValue(jsonString, typeReference);
    } catch (IOException e) {
      throw new JsonException(String.format("Unable to read JSON object '%s' from input '%s'",
          new Object[] {typeReference.getType(), jsonString}), e);
    }
  }

  public static <T> T readJson(byte[] jsonByte, TypeReference<T> typeReference) {
    try {
      return (T) MAPPER.readValue(new String(jsonByte, "UTF-8"), typeReference);
    } catch (IOException e) {
      throw new JsonException(String.format("Unable to read JSON object '%s' from input '%s'",
          new Object[] {typeReference.getType(), jsonByte}), e);
    }
  }

  public static JsonNode readJson(String jsonString) {
    try {
      return MAPPER.readTree(jsonString);
    } catch (IOException e) {
      throw new JsonException(
          String.format("Unable to read JSON node from input '%s'", new Object[] {jsonString}), e);
    }
  }

  public static Map<String, Object> readJsonToMap(String jsonString) {
    return (Map) readJson(jsonString, new TypeReference<HashMap<String, Object>>() {

    });
  }

  public static List<Map<String, Object>> readJsonToList(String jsonString) {
    return (List) readJson(jsonString, new TypeReference<List<Map<String, Object>>>() {

    });
  }

  public static <T> String toJson(T object) {
    try {
      return MAPPER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new JsonException(String.format("Unable to write JSON object '%s'",
          new Object[] {object.getClass().getSimpleName()}), e);
    }
  }

  public static <T> JsonNode toJsonNode(T object) {
    String jsonString = toJson(object);
    return readJson(jsonString);
  }

  public static <T> String toJson(T object, ObjectMapper mapper) {
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new JsonException(String.format("Unable to write JSON object '%s'",
          new Object[] {object.getClass().getSimpleName()}), e);
    }
  }

  public static <T> T readJsonFromFile(String resourcePath, Class<T> clazz) {
    try {
      File file = new File(resourcePath);
      String result = Files.toString(file, Charsets.UTF_8);
      return (T) MAPPER.readValue(result, clazz);
    } catch (IOException e) {
      throw new JsonException(
          String.format("Unable to read JSON object '%s' from resource path '%s'",
              new Object[] {clazz.getSimpleName(), resourcePath}),
          e);
    }
  }

  public static JsonNode readJsonNodeFromFile(String resourcePath) {
    File file = new File(resourcePath);
    try (BufferedReader bufferedReader = Files.newReader(file, Charsets.UTF_8)) {
      return MAPPER.readTree(bufferedReader);
    } catch (IOException e) {
      throw new JsonException(String.format("Unable to read JSON node from resource path '%s'",
          new Object[] {resourcePath}), e);
    }
  }

  public static void writeJsonToFile(String resourcePath, String jsonString) {
    try {
      File file = new File(resourcePath);
      Files.write(jsonString, file, Charsets.UTF_8);
    } catch (IOException e) {
      throw new JsonException(
          String.format("Unable to write file to resourcePath '%s'", new Object[] {resourcePath}),
          e);
    }
  }

  public static String unprettify(String jsonString) {
    try {
      JsonNode node = (JsonNode) MAPPER.readValue(jsonString, JsonNode.class);
      return MAPPER.writeValueAsString(node);
    } catch (IOException e) {
      throw new JsonException(
          String.format("Unable to read JSON node from input '%s'", new Object[] {jsonString}), e);
    }
  }

  public static String prettify(String jsonString) {
    try {
      JsonNode node = (JsonNode) MAPPER.readValue(jsonString, JsonNode.class);
      ObjectWriter writer = MAPPER.writer(new DefaultPrettyPrinter());
      return writer.writeValueAsString(node);
    } catch (IOException e) {
      throw new JsonException(
          String.format("Unable to read JSON node from input '%s'", new Object[] {jsonString}), e);
    }
  }

  public static String prettify(Object object) {
    try {
      JsonNode node = (JsonNode) MAPPER.convertValue(object, JsonNode.class);
      ObjectWriter writer = MAPPER.writer(new DefaultPrettyPrinter());
      return writer.writeValueAsString(node);
    } catch (IOException e) {
      throw new JsonException(
          String.format("Unable to read JSON node from input '%s'", new Object[] {object}), e);
    }
  }

  public static String merge(String sourceContent, String addedContent) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    JsonNode sourceRoot = mapper.readTree(sourceContent);
    JsonNode addedRoot = mapper.readTree(addedContent);

    Map<String, JsonNode> sourcetHash = getDatas(sourceRoot);
    Map<String, JsonNode> addedtHash = getDatas(addedRoot);

    for (String key : addedtHash.keySet()) {

      if (sourcetHash.containsKey(key)) {
        sourcetHash.put(key,
            deepMerge((ObjectNode) addedtHash.get(key), (ObjectNode) sourcetHash.get(key)));
        continue;
      }
      ((ObjectNode) sourceRoot).putPOJO(key, addedtHash.get(key));
    }

    sourcetHash.clear();
    addedtHash.clear();

    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sourceRoot);
  }

  private static Map<String, JsonNode> getDatas(JsonNode nodes) {
    Map<String, JsonNode> hash = new HashMap<String, JsonNode>();

    Iterator<Map.Entry<String, JsonNode>> it = nodes.fields();


    while (it.hasNext()) {
      Map.Entry<String, JsonNode> ent = (Map.Entry) it.next();
      hash.put(ent.getKey(), ent.getValue());
    }

    return hash;
  }

  private static ObjectNode deepMerge(ObjectNode added, ObjectNode source) {
    Iterator<String> keys = added.fieldNames();


    while (keys.hasNext()) {
      String key = (String) keys.next();
      JsonNode node = added.get(key);

      source.replace(key, node);
    }

    return source;
  }
}
