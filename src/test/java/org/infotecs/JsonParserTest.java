package org.infotecs;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class JsonParserTest {

    JsonParser parser;

    @BeforeTest
    public void createParser() {
        parser = new JsonParser();
    }

    @Test(dataProvider = "parsingResults", dataProviderClass = DP.class)
    public void testWriting(List<Student> students, boolean expected) {
        Assert.assertEquals(parser.write(students, "local.json"), expected, "FAILED testWriting");
    }
}
