package com.hbvhuwe.musicshop.providers;

import com.hbvhuwe.musicshop.model.Composition;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.model.Group;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.util.zip.DeflaterInputStream;

import static org.junit.Assert.*;

public class TestJpa {
    private DataProvider<Group> groupDataProvider = new JpaProvider<>(Group.class);
    private DataProvider<Disk> diskDataProvider = new JpaProvider<>(Disk.class);
    private DataProvider<Composition> compositionDataProvider = new JpaProvider<>(Composition.class);

    @Test
    public void testConnection() {
        Assert.assertTrue(JpaProvider.sessionFactory.isOpen());
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
        JpaProvider.close();
    }
}