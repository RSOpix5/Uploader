package rso.rsopix5.commons;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.postgresql.util.PGobject;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.Converter;
import java.io.StringReader;
import java.util.Arrays;

@Converter(autoApply = true)
public class SubmissionAlbumiJsonConverter implements javax.persistence.AttributeConverter<Integer[], Object> {

    private static final long serialVersionUID = 1L;
    private static ObjectMapper mapper = new ObjectMapper();

    public Object convertToDatabaseColumn(Integer[] objectValue) {
        try {
            PGobject out = new PGobject();
            out.setType("json");
            Integer[]  objectValue1 = objectValue;
            if(objectValue == null) {
                objectValue1 = new Integer[0];
            }
            String outValue = String.format("{\"albums\": \"%s\"}", Arrays.toString(objectValue1));
            out.setValue(outValue);
            return out;
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to serialize to json field ", e);
        }
    }



    public Integer[] convertToEntityAttribute(Object dataValue) {
        try {
            if (dataValue instanceof PGobject && ((PGobject) dataValue).getType().equals("json")) {
                PGobject val = (PGobject) dataValue;
                String s = val.toString();

                JsonReader jsonReader = Json.createReader(new StringReader(s));
                JsonObject object = jsonReader.readObject();
                jsonReader.close();
                String[] array = object.get("albums").toString().split("\\[")[1].split("\\]")[0].split(",");
                if(array.length == 1 && array[0].length() == 0) {
                    return new Integer[0];
                }
                Integer[] rt = new Integer[array.length];
                for(int i = 0; i < array.length; i++) {
                    try {
                        rt[i] = new Integer(array[i]);
                    }catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                        rt[i] = new Integer("-1");
                    }
                }
                return rt;
            }
            return new Integer[0];
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to deserialize to json field ", e);
        }
    }
}