package net.alkitbrowser;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Settings {

    StringBuffer nameOS = new StringBuffer("Windows XP");
    int systemNumber = 0;
    List<String> searchSystemList = new ArrayList<>();

}