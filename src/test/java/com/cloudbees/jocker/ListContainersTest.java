package com.cloudbees.jocker;

import org.junit.Test;

import java.io.IOException;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class ListContainersTest {

    @Test
    public void should_list_containers() throws IOException {
        System.out.println(
                new Docker().ps().execute());
    }
}
