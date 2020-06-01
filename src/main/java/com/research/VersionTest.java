//package com.codility;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import static org.assertj.core.api.Assertions.*;
//
//public class VersionTest {
//    // ... write your unit tests here ...
//           @Test(expected=IllegalArgumentException.class)
//    public void testNull(){
//    	 Version version =new Version(null);
//    	  Assert.assertNull(version);
//    // 	  System.out.println(errorVersionMustNotBeNull);
//    }
//           @Test(expected=IllegalArgumentException.class)
//    public void testEndWiths(){
//    	 Version version =new Version("3.8.0-SNAPSHOT");
//    	  Assert.assertTrue(s.endsWith("-SNAPSHOT"));
//    	  System.out.println(errorVersionMustMatchPattern);
//    }
//
//    
//    @Test
//    public void exampleTest() {
//        Version version = new Version("3.8.0");
//        // ...
//    }
//  
//    // expected error messages:
//
//    static final String errorVersionMustNotBeNull = "'version' must not be null!";
//    static final String errorOtherMustNotBeNull =  "'other' must not be null!";
//    static final String errorVersionMustMatchPattern =  "'version' must match: 'major.minor.patch(-SNAPSHOT)'!";
//}
