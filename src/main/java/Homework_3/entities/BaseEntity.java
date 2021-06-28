package Homework_3.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract public class BaseEntity {

    protected final Logger log;
    private String loggerName;

    public BaseEntity(String loggerName) {
        this.log = LogManager.getLogger(loggerName);
    }

}
