package isa.qa.project.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * LocalDate Jackson Serializer Converter
 *
 * @author May
 * @version 1.0
 * @date 2020/1/1 20:11
 */
public class LocalDateToLongConverter extends JsonSerializer<LocalDate> {

	@Override
	public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeNumber(LocalDateTime.of(localDate, LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli());
	}
}
