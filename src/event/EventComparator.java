package event;

import java.util.*;

public class EventComparator implements Comparator<Event>{ 
    
    // Overriding compare() method of Comparator  
    // for ascending order of timestamps 
    public int compare(Event e1, Event e2) { 
        if (e1.time < e2.time) 
            return -1; 
        else if (e1.time > e2.time) 
            return 1; 
        return 0; 
    } 
} 