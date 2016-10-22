import java.util.Scanner;

class Sol {
    Mystack core = new Mystack();
    Mystack preCore = new Mystack();

    public void work() {  //�����������ܺ���
        preCore.clear();
        Scanner cin = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String str = cin.nextLine();
            str = str.trim();
            if (str.charAt(0) != '!') {
                core.clear();
                //if(s.charAt(0) == '-' || s.charAt(0) == '+') s = '0' + s;
                if (isExpression(str)) {
                    preCore = core;
                    System.out.println(str);
                } else {
                    System.out.println("Error , expression error !");
                }
            } else {
                if (preCore.empty()) {
                    System.out.println("Error , no expression !");
                } else if (str.charAt(1) == 's') {
                    if (isSimplify(str)) {
                        simplify(str);
                    } else {
						System.out.println("Error , format error !");
					}
                } else {
                    if (isDerivative(str)) {
                        derivative(str);
                    } else {
						System.out.println("Error , format error !");
					}
                }
            }
            //System.out.println(s);
        }
    }

    private void simplify(String str) { //����
        str = str.substring(9, str.length());
        str = str.trim();
        preCore.simplify(str);
    }

    private void derivative(String str) { //��
        str = str.substring(4);
        str = str.trim();
        preCore.derivative(str);
    }

    private Boolean isSimplify(String str) { //�����ж�
        if (str.length() < 9) {
            return false;
        }
        if (!str.substring(1, 9).equals("simplify")) {
            return false;
        }
        return true;
    }

    private Boolean isDerivative(String str) { //���ж�
        if (str.length() < 4) {
            return false;
        }
        if (!str.substring(1, 4).equals("d/d")) {
            return false;
        }
        return true;
    }

    private Boolean isExpression(String str) { //���ʽ�ж�
        for (int i = 0; i < str.length(); i++) {
            if (!isSymbol(str.charAt(i))) {
                return false;
            }
        }
        int current = 0;
        for (int i = 1; i < str.length(); i++) {
            char operator = str.charAt(i);
            if (operator == '-' || operator == '+') {
                if (!core.add(str.substring(current, i))) {
					return false;
				}
                current = i;
            }
        }
        if (!core.add(str.substring(current, str.length()))) {
			return false;
		}
        return true;
    }

    private Boolean isSymbol(char cha) { //�����ж�
        if (cha == 43 || cha == 45 || cha == 42 ||
        		cha <= 57 && cha >= 48 || 
        		cha <= 122 && cha >= 97 || 
        		cha <= 90 && cha >= 65) {
            return true;
        }
        return false;
    }
}
