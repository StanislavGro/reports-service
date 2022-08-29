package ru.isands.reportsservice.utill;

import ru.isands.reportsservice.entity.enums.Names;
import ru.isands.reportsservice.entity.enums.Values;

public class RandomStatisticValues {
    private final boolean isNumeric;

    public RandomStatisticValues(){
        this.isNumeric = ((int) (Math.random() * 2)) == 0;
    }

    public boolean isNumeric() {
        return isNumeric;
    }

    public String getRandomName(){
        return isNumeric ? Names.NUMBER.getValue() : Names.STRING.getValue();
    }

    public String getRandomValue(){
        return isNumeric ? String.valueOf((int) (Math.random() * 100)) : Values.STRING.getValue() + (int) (Math.random() * 100);
    }

}
