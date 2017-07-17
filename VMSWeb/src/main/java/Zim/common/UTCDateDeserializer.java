//package Zim.common;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
//
//import java.io.IOException;
//import java.util.Date;
//
//public class UTCDateDeserializer extends DateDeserializers.DateDeserializer {
//
//    @Override
//    protected Date _parseDate(JsonParser jp, DeserializationContext ctxt)
//            throws IOException, JsonProcessingException {
//        Date _parseDate = null;
//        try {
//            _parseDate = super._parseDate(jp, ctxt);
//        } catch (Exception ex) {
//
//        }
//        return _parseDate;
//    }
//
//}
