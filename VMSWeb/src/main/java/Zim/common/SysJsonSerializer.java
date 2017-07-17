//package Zim.common;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.mongodb.DBObject;
//import com.mongodb.DBRef;
//import org.springframework.core.convert.ConversionService;
//import org.springframework.data.mapping.context.MappingContext;
//import org.springframework.data.mongodb.core.convert.MongoConverter;
//import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
//import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
//import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
//import org.springframework.data.util.TypeInformation;
//
//import javax.xml.datatype.DatatypeConfigurationException;
//import javax.xml.datatype.DatatypeFactory;
//import javax.xml.datatype.XMLGregorianCalendar;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
///**
// * Created by Laxton-Joe on 2017/7/17.
// */
//public class SysJsonSerializer {
//
//
//    public class XMLGregorianCalendarDeserializer implements MongoConverter {
//        @Override
//        public MongoTypeMapper getTypeMapper() {
//            return null;
//        }
//
//        @Override
//        public MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> getMappingContext() {
//            return null;
//        }
//
//        @Override
//        public ConversionService getConversionService() {
//            return null;
//        }
//
//        @Override
//        public <R extends T> R read(Class<R> type, DBObject source) {
//            return null;
//        }
//
//        @Override
//        public Object convertToMongoType(Object obj) {
//            return null;
//        }
//
//        @Override
//        public Object convertToMongoType(Object obj, TypeInformation<?> typeInformation) {
//            return null;
//        }
//
//        @Override
//        public DBRef toDBRef(Object object, MongoPersistentProperty referingProperty) {
//            return null;
//        }
//
//        @Override
//        public void write(Object source, DBObject sink) {
//
//        }
//
//
////        @Override
////        public XMLGregorianCalendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
////            XMLGregorianCalendar xmlGregorianCalendar = null;
////            if (p.getText().length() > 0) {
////                try {
////
////                    GregorianCalendar gc = new GregorianCalendar();
////                    gc.setTimeInMillis(Long.parseLong(p.getText()));
////
////                    xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
////
////                } catch (DatatypeConfigurationException e) {
////                    e.printStackTrace();
////                }
////            }
////            return xmlGregorianCalendar;
////        }
//
//    }
//}
