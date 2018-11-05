package com.mu.sview;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

public class FieldPrimitiveTest {

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {

        Class<Save> saveClass = Save.class;
        Field field = saveClass.getDeclaredField("id");

        System.out.println(field.getType().getName());


        Save save = new Save();
        field.setAccessible(true);
        field.set(save,233);

    }
}


class Save {
    List<String> id;
}
