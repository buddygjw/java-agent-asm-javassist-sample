package agent.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Package agent.asm
 * @Description: TODO
 * @Author guojianwei@jd.com
 * @Date 2016/12/30
 * @Time 10:31
 * @Version V1.0
 */
public class JavassistClassFileTransformer  implements ClassFileTransformer {


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        System.out.println(className);
        if (className.indexOf("other/service")>-1) {
            // Javassist
            return javassist();
        }else{
            return classfileBuffer;
        }
    }

    private byte[] javassist(){
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("other.service.Stuff");
            CtMethod m = cc.getDeclaredMethod("run");
            m.addLocalVariable("elapsedTime", CtClass.longType);
            m.insertBefore("elapsedTime = System.currentTimeMillis();");
            m.insertAfter("{elapsedTime = System.currentTimeMillis() - elapsedTime;"
                    + "System.out.println(\"执行此方法总耗时: \" + elapsedTime+\"ms\");}");
            byte[] byteCode = cc.toBytecode();
            cc.detach();
            return byteCode;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}