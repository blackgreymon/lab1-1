public class Node { //单项式处理类
    String varName;
    public String[] vars = new String[100]; //多字母变量数组
    public int num[] = new int[100]; //系数数组
    public int coe;
    public int top;

    public Node() {
    }

    public Node(Node node) {
        for (int i = 0; i < 100; i++) {
			vars[i] = node.vars[i];
		}
        for (int i = 0; i < 100; i++) {
			num[i] = node.num[i];
		}
        coe = node.coe;
        top = node.top;
    }

    public Node(String name, int acoe) { //将单项式拆分为多变量存储，分别记录系数和指数信息
        varName = name;
        coe = acoe;
        int current = 0;
        top = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '*') {
                num[top] = 0;
                vars[top++] = name.substring(current, i);
                current = i + 1;
            }
        }
        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++) {
                if (vars[j].equals(vars[i])) {
                    num[j]++;
                    break;
                }
            }
        }
    }
}