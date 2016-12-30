package agent.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CustomClassVisitor extends ClassVisitor {

    /**
     * 是否是接口类
     */
    private boolean isInterface;

    public CustomClassVisitor(ClassWriter writer) {
        super(Opcodes.ASM4, writer);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);

    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {


        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

        /**
         * 是否是抽象方法
         */
        boolean is_abstract;
        // 判断是否是抽象方法
        if ((access & Opcodes.ACC_ABSTRACT) == 0) {
            is_abstract = false;
        } else {
            is_abstract = true;
        }

        // 判断这个方法是否达到添加切面链的条件

        if (!is_abstract && !isInterface && mv != null && (!(name.equals("<init>") || "<clinit>".equals(name)))) {

            try {
                mv = new CustomMethodVisitor(Opcodes.ASM4, mv);
            } catch (Exception e) {
            }

        }
        return mv;


    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}