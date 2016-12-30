package agent.asm;

import agent.asm.CustomClassVisitor;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Package agent
 * @Description: TODO
 * @Author guojianwei@jd.com
 * @Date 2016/12/29
 * @Time 17:52
 * @Version V1.0
 */
public class AsmClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className.indexOf("other/service")>-1) {
            // ASM Code
//            System.out.println(className);
            return asm(classfileBuffer);
        }else{
//            System.out.println(className);
            return classfileBuffer;
        }
    }

    private byte[] asm(byte[] bytes){
        ClassReader reader = new ClassReader(bytes);
        ClassWriter writer = new ClassWriter(reader, 0);
        CustomClassVisitor visitor = new CustomClassVisitor(writer);
        reader.accept(visitor, 0);
        return writer.toByteArray();
    }

}
