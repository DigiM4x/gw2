package com.daemon.job;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DataBindingException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.core.Utils;
import com.db.DB;
import com.db.types.WatchList;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.types.response.Item;

public class WatchListCleanupJob extends DaemonJob{
   @Override
   public void run() {
     
   }

}
