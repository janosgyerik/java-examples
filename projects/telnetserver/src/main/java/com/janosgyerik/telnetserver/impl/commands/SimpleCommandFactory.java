package com.janosgyerik.telnetserver.impl.commands;

import com.janosgyerik.telnetserver.commands.Command;
import com.janosgyerik.telnetserver.commands.CommandFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleCommandFactory implements CommandFactory {
    @Override
    public Command createCommand(Class<? extends Command> klass, File execdir) throws CommandInstantiationException {
        try {
            Constructor<? extends Command> ctor = klass.getConstructor(File.class);
            return ctor.newInstance(execdir);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new CommandInstantiationException("Could not create command: " + klass, e);
        }
    }
}
