package agent.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @Package agent.asm
 * @Description: TODO
 * @Author guojianwei@jd.com
 * @Date 2016/12/29
 * @Time 20:32
 * @Version V1.0
 */
public class CustomMethodVisitor extends MethodVisitor {

    public CustomMethodVisitor(int i) {

        super(i);
    }

    public CustomMethodVisitor(int i, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
    }

    @Override
    public void visitCode() {
        //此方法在访问方法的头部时被访问到，仅被访问一次
        visitMethodInsn(Opcodes.INVOKESTATIC, Monitor.class.getName().replace(".","/"),  "start", "()V",false);
        super.visitCode();

    }

    @Override
    public void visitInsn(int opcode) {
        //此方法可以获取方法中每一条指令的操作类型，被访问多次
        //如应在方法结尾处添加新指令，则应判断：
        if(opcode == Opcodes.RETURN)
        {
            visitMethodInsn(Opcodes.INVOKESTATIC, Monitor.class.getName().replace(".","/"),  "end", "()V",false);
        }
        super.visitInsn(opcode);
    }


}
