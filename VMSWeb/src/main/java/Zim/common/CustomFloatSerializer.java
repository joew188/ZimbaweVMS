package Zim.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.NumberFormat;

/**
 * Created by Laxton-Joe on 2017/9/20.
 */
public class CustomFloatSerializer extends JsonSerializer<Float> {


    @Override
    public void serialize(Float value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        NumberFormat ddf1 = NumberFormat.getNumberInstance();
        ddf1.setGroupingUsed(false);
        ddf1.setMaximumFractionDigits(2);
        gen.writeNumber(ddf1.format(value));
    }
}