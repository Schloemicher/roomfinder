package at.htl.roomfinder.business;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Initbean {

    public Initbean() {
    }

    @PostConstruct
    public void init(){

    }
}
