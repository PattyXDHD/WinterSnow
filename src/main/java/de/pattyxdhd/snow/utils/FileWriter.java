package de.pattyxdhd.snow.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWriter {


    private File f;
    private YamlConfiguration c;

    public FileWriter(String FilePath, String FileName) {
        this.f = new File(FilePath, FileName);
        this.c = YamlConfiguration.loadConfiguration(this.f);
    }

    public boolean exist() {
        return f.exists();
    }

    public FileWriter setValue(String ValuePath, Object Value) {
        c.set(ValuePath, Value);
        save();
        return this;
    }

    public FileWriter setDefaultValue(String ValuePath, Object Value) {
        if(!valueExist(ValuePath)){
            c.set(ValuePath, Value);
            save();
        }
        return this;
    }

    public Object getObject(String ValuePath) {
        return c.get(ValuePath);
    }

    public boolean valueExist(String valuePath){
        return getObject(valuePath) != null;
    }

    public FileWriter save() {
        try {
            this.c.save(this.f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean getBoolean(String ValuePath) {
        return c.getBoolean(ValuePath);
    }

    public String getString(String ValuePath) {
        return c.getString(ValuePath);
    }

    public Integer getInt(String ValuePath) {
        return c.getInt(ValuePath);
    }

    public List<String> getStringList(String ValuePath) {
        return c.getStringList(ValuePath);
    }

    public List<Integer> getIntList(String ValuePath) {
        return c.getIntegerList(ValuePath);
    }

    public double getDouble(String ValuePath) {
        return c.getDouble(ValuePath);
    }

    public String getFormatString(String ValuePath) {
        return c.getString(ValuePath).replaceAll("&", "§");
    }

}
