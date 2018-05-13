package com.hbvhuwe.musicshop.providers;

import com.hbvhuwe.musicshop.model.Composition;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.model.Group;
import org.junit.*;

public class TestJdbc {
    private DataProvider<Group> groupDataProvider = new JdbcProvider<>(Group.class);
    private DataProvider<Disk> diskDataProvider = new JdbcProvider<>(Disk.class);
    private DataProvider<Composition> compositionDataProvider = new JdbcProvider<>(Composition.class);

    @Test
    public void testConnection() {
        Assert.assertTrue(JdbcProvider.isConnected);
    }

    @Test
    public void testGroupList() {
        try {
            Assert.assertFalse(groupDataProvider.selectAll().isEmpty());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testDiskList() {
        try {
            Assert.assertFalse(diskDataProvider.selectAll().isEmpty());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testCompositionList() {
        try {
            Assert.assertFalse(compositionDataProvider.selectAll().isEmpty());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @AfterClass
    public static void close() {
        try {
            JdbcProvider.close();
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
