package com.hbvhuwe.musicshop.providers;

import com.hbvhuwe.musicshop.model.Composition;
import com.hbvhuwe.musicshop.model.Disk;
import com.hbvhuwe.musicshop.model.Group;
import org.junit.Assert;
import org.junit.Test;

public class TestNet {
    private DataProvider<Group> groupDataProvider = DataProvider.of(Providers.NET, Group.class);
    private DataProvider<Disk> diskDataProvider = DataProvider.of(Providers.NET, Disk.class);
    private DataProvider<Composition> compositionDataProvider = DataProvider.of(Providers.NET, Composition.class);

    @Test
    public void testGroupList() {
        try {
            Assert.assertFalse(groupDataProvider.selectAll().isEmpty());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testDiskList() {
        try {
            Assert.assertFalse(diskDataProvider.selectAll().isEmpty());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testCompositionList() {
        try {
            Assert.assertFalse(compositionDataProvider.selectAll().isEmpty());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
