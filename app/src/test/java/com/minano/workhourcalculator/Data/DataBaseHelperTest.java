package com.minano.workhourcalculator.Data;

import android.content.Context;
import android.test.suitebuilder.annotation.LargeTest;

import com.minano.workhourcalculator.Data.Entity.WorkHourDay;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Admin on 07/02/2017.
 */

//@RunWith(MockitoJUnitRunner.class)
@RunWith(JUnit4.class)
public class DataBaseHelperTest {

//    @Mock
//    private Context context;
//    private DataBaseHelper dataBaseHelper;
//
//    @Before
//    public void setUp(){
//        dataBaseHelper = new DataBaseHelper(InstrumentationRegistry.getTargetContext());
//
//        WorkHourDay item = new WorkHourDay();
//        item.setNumberOfWorkHours(8);
//        item.setId(1);
//        item.setDate("7.02.2017");
//
//        dataBaseHelper.storeItem(item);
//
//        WorkHourDay item2 = new WorkHourDay();
//        item2.setNumberOfWorkHours(8);
//        item2.setId(1);
//        item2.setDate("8.02.2017");
//
//        dataBaseHelper.storeItem(item2);
//    }
//
//    @Test
//    public void getItemWhenGivenItem(){
//        WorkHourDay item = dataBaseHelper.getItem(1);
//
//        assertThat(item.getId(), is(1));
//        assertTrue(item.getDate().equals("7.02.2017"));
//        assertThat(item.getNumberOfWorkHours(), is(8));
//    }
}
