package isa.qa.project.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 时间戳转LocalDateTime
 *
 * @author May
 * @version 1.0
 * @date 2020/1/16 19:35
 */
public class LongToLocalDateTimeConvert extends JsonDeserializer<LocalDateTime> {
	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode timestamp = jsonParser.getCodec().readTree(jsonParser);

		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.asLong()), ZoneId.systemDefault());
	}
}
