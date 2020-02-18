package isa.qa.project.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * String(yyyy-MM-dd HH:mm:ss) convert to LocalDateTime
 *
 * @author May
 * @version 1.0
 * @date 2020/1/12 10:09
 */
public class StringToLocalDateTimeConverter extends JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		JsonNode localDateTime = jsonParser.getCodec().readTree(jsonParser);

		return LocalDateTime.parse(localDateTime.asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
