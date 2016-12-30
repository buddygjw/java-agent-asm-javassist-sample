package agent;

import agent.asm.AsmClassFileTransformer;
import agent.javassist.JavassistClassFileTransformer;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {

        inst.addTransformer(new AsmClassFileTransformer());
//        inst.addTransformer(new JavassistClassFileTransformer());

    }


}

