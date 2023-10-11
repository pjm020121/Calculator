import java.util.Scanner;
import java.util.ArrayList;
public class Calculator {
    public static String Plus(char[] n1, char[] n2){

        int[] hap = new int[n1.length + n2.length];

        int len = 0;
        int size = 0;

        // n1과 n2중 크기가 큰 것과 작은 것을 분류
        if (n1.length >= n2.length){
            len = n1.length;
            size = n2.length;
        }

        else if (n2.length > n1.length){
            len = n2.length;
            size = n1.length;
        }

        // 숫자형으로 배열 선언
        int[] n1_copy = new int[n1.length];
        int[] n2_copy = new int[n2.length];

        // 숫자로 대입
        for (int i = 0; i < n1_copy.length; i++) {
            n1_copy[i] = Character.getNumericValue(n1[i]);
        }

        for (int i = 0; i < n2_copy.length; i++) {
            n2_copy[i] = Character.getNumericValue(n2[i]);
        }

        // n1과 n2값을 hap에 추가하고 10이상시 앞의 원소에 숫자 추가
        for (int i = 0; i < size; i++){
            hap[hap.length - (i + 1)] += (n1_copy[n1_copy.length - (i + 1)] + n2_copy[n2_copy.length - (i + 1)]);
            if (hap[hap.length - (i + 1)] > 9) {
                hap[hap.length - (i + 1)] -= 10;
                hap[hap.length - (i + 2)] += 1;
            }
        }

        // 길이가 다를 경우 앞자리 넣어주기
        if (n1.length > n2.length){
            for (int i = 0; i < n1.length - n2.length; i++){
                hap[(hap.length - (i + 1)) - n2.length] += n1_copy[(n1_copy.length - (i + 1)) - n2_copy.length];
            }
        }

        else if (n1.length < n2.length){
            for (int i = 0; i < n2.length - n1.length; i++){
                hap[(hap.length - (i + 1)) - n1.length] += n2_copy[(n2_copy.length - (i + 1)) - n1_copy.length];
            }
        }

        // 리턴값을 받을 문자열 생성
        String result = Remove_Zero(hap);
        return result;
    }

    public static String Minus(char[] n1, char[] n2){

        // 변수선언
        int len = 0;
        int size = 0;
        boolean count = true;
        int temp = 0;
        int min = 0;

        // 숫자형 배열 선언
        int[] n1_copy = new int[n1.length];
        int[] n2_copy = new int[n2.length];

        // 문자열을 숫자로 바꿔주기
        for(int i = 0; i < n1_copy.length; i++){
            n1_copy[i] = Character.getNumericValue(n1[i]);
        }

        for(int i = 0; i < n2_copy.length; i++){
            n2_copy[i] = Character.getNumericValue(n2[i]);
        }

        // 리턴값을 받을 문자열 생성
        String value = Remove_Zero(n1_copy);
        n1_copy = new int[value.length()];

        // 문자열을 숫자형 배열에 할당
        for(int i = 0; i < value.length(); i++){
            char ch = value.charAt(i);
            n1_copy[i] = Character.getNumericValue(ch);
        }

        // Line 87과 동일
        String value2 = Remove_Zero(n2_copy);
        n2_copy = new int[value2.length()];

        // Line 91과 동일
        for(int i = 0; i < value2.length(); i++){
            char ch = value2.charAt(i);
            n2_copy[i] = Character.getNumericValue(ch);
        }

        // 각 길이변수들에 다시 길이 대입
        if (n1_copy.length >= n2_copy.length){
            len = n1_copy.length;
            size = n2_copy.length;
        }

        else if (n2_copy.length > n1_copy.length){
            len = n2_copy.length;
            size = n1_copy.length;
        }

        // 공간 결과 넣을 공간만들기
        int[] hap = new int[len];

        // 한자리수끼리의 연산
        if (n1_copy.length == 1 && n2_copy.length == 1){
            hap[0] += n1_copy[0] - n2_copy[0];
        }

        if (n1.equals(n2)){
            hap[0] = 0;
        }

        // n1_copy의 길이가 더 긴 경우 실행
        else if (n1_copy.length > n2_copy.length){
            for (int i = 0; i < size; i++) {

                // n2_copy가 n1_copy보다 클 경우 크기를 내려받아 연산하는 if문
                if (n1_copy[n1_copy.length - (i + 1)] < n2_copy[n2_copy.length - (i + 1)] && 0 < (n1_copy.length - (i + 1))) {
                    n1_copy[n1_copy.length - (i + 1)] += 10;
                    n1_copy[n1_copy.length - (i + 2)] -= 1;
                }
                hap[hap.length - (i + 1)] += n1_copy[n1_copy.length - (i + 1)] - n2_copy[n2_copy.length - (i + 1)];
            }

            // 길이가 다를 경우 앞자리 넣어주기
            if (n1_copy.length > n2_copy.length && hap[0] == 0){
                for (int i = 0; i < hap.length - n2_copy.length; i++){
                    hap[(hap.length - (i + 1)) - n2_copy.length] += n1_copy[(n1_copy.length - (i + 1)) - n2_copy.length];
                }
            }

            // hap에 0보다 작은 수가 있으면 내림해서 사용하는 if문
            if (hap.length > 1){
                while (count){
                    if (hap.length - (temp + 1) <= 1){
                        count = false;
                    }
                    if (hap[hap.length - (temp + 1)] < 0){
                        hap[hap.length - (temp + 1)] += 10;
                        hap[hap.length - (temp + 2)] -= 1;
                    }
                    temp++;
                }
            }
        }

        // n1과 n2의 길이가 같은 경우
        else if (n2_copy.length == n1_copy.length && n1.length > 1 && n2_copy.length > 1){

            // n2의 첫자리가 n1의 첫자리보다 클때
            if (n2_copy[0] > n1_copy[0]) {
                for (int i = 0; i < size; i++) {
                    if (n1_copy[n1_copy.length - (i + 1)] > n2_copy[n2_copy.length - (i + 1)]) {
                        n2_copy[n2_copy.length - (i + 1)] += 10;
                        n2_copy[n2_copy.length - (i + 2)] -= 1;
                    }
                    hap[hap.length - (i + 1)] += n2_copy[n2_copy.length - (i + 1)] - n1_copy[n1_copy.length - (i + 1)];
                }

                // hap에서 0보다 큰 첫번째 원소의 부호교체
                while (true){
                    if(hap[min] > 0){
                        hap[min] = -hap[min];
                        break;
                    }
                    min++;
                }
            }

            // n1의 첫자리가 n2의 첫자리보다 클 때
            else if (n1_copy[0] > n2_copy[0]){
                for (int i = 0; i < size; i++){
                    if (n2_copy[n2_copy.length - (i + 1)] > n1_copy[n1_copy.length - (i + 1)] && 0 < (n1_copy.length - (i + 1))){
                        n1_copy[n1_copy.length - (i + 1)] += 10;
                        n1_copy[n1_copy.length - (i + 2)] -= 1;
                    }
                    hap[hap.length - (i + 1)] += n1_copy[n1_copy.length - (i + 1)] - n2_copy[n2_copy.length - (i + 1)];
                }
            }

            // n1의 첫자리와 n2의 첫자리가 같은 경우
            else if (n1_copy[0] == n2_copy[0]){
                min = 1;

                    // n1의 값이 n2의 값보다 클때 연산
                    if (n1_copy[min] > n2_copy[min]){
                        for (int i = 0; i < size; i++){
                            if (n2_copy[n2_copy.length - (i + 1)] > n1_copy[n1_copy.length - (i + 1)] && 0 < (n1_copy.length - (i + 1))){
                                n1_copy[n1_copy.length - (i + 1)] += 10;
                                n1_copy[n1_copy.length - (i + 2)] -= 1;
                            }
                            hap[hap.length - (i + 1)] += n1_copy[n1_copy.length - (i + 1)] - n2_copy[n2_copy.length - (i + 1)];
                        }
                    }

                    // n2의 값이 n1의 값보다 클때 연산
                    else if (n2_copy[min] > n1_copy[min]){
                        for (int i = 0; i < size; i++){
                            if (n1_copy[n1_copy.length - (i + 1)] > n2_copy[n2_copy.length - (i + 1)] && 0 < (n2_copy.length - (i + 1))) {
                                n2_copy[n2_copy.length - (i + 1)] += 10;
                                n2_copy[n2_copy.length - (i + 2)] -= 1;
                            }
                            hap[hap.length - (i + 1)] += n2_copy[n2_copy.length - (i + 1)] - n1_copy[n1_copy.length - (i + 1)];
                        }

                        // hap에서 0보다 큰 첫번째 원소의 부호교체
                        while (true){
                            if(hap[min] > 0){
                                hap[min] = -hap[min];
                                break;
                            }
                            min++;
                        }
                    }
            }
        }

        // n2_copy의 길이가 더 긴 경우
        else if (n2_copy.length > n1_copy.length){

            for (int i = 0; i < size; i++){

                // n1_copy가 n2_copy보다 클 경우 수를 내려받아 연산하는 if문
                if (n1_copy[n1_copy.length - (i + 1)] > n2_copy[n2_copy.length - (i + 1)]){
                    n2_copy[n2_copy.length - (i + 1)] += 10;
                    n2_copy[n2_copy.length - (i + 2)] -= 1;
                }
                hap[hap.length - (i + 1)] += n2_copy[n2_copy.length - (i + 1)] - n1_copy[n1_copy.length - (i + 1)];
            }

            // n2_copy의 길이가 n1_copy의 길이보다 긴 경우 hap에 넣어주는 if문
            if (n2_copy.length > n1_copy.length && hap[0] == 0){
                for (int i = 0; i < hap.length - n1_copy.length; i++){
                    hap[(hap.length - (i + 1)) - n1_copy.length] += n2_copy[(n2_copy.length - (i + 1) - n1_copy.length)];
                }
            }

            // hap에 0보다 작은 수가 있으면 내림해서 사용하는 if문
            if (hap.length > 1){
                while (count){
                    if (hap.length - (temp + 1) <= 1){
                        count = false;
                    }
                    if (hap[hap.length - (temp + 1)] < 0){
                        hap[hap.length - (temp + 1)] += 10;
                        hap[hap.length - (temp + 2)] -= 1;
                    }
                    temp++;
                }
            }

            // hap에서 0보다 큰 첫번째 원소의 부호교체
            while (true){
                if(hap[min] > 0){
                    hap[min] = -hap[min];
                    break;
                }
                min++;
            }
        }

        String result = Remove_Zero(hap);
        return result;
    }

    public static String Multiply(char[] n1, char[] n2){

        int len = n1.length;
        int size = n2.length;
        int count = 0;
        int n = 0;

        // 숫자형 배열 선언
        int [] hap = new int[n1.length + n2.length];
        int [] n1_copy = new int[n1.length];
        int [] n2_copy = new int[n2.length];

        // 문자를 숫자로 변경
        for (int i = 0; i < n1.length; i++){
            n1_copy[i] = Character.getNumericValue(n1[i]);
        }

        for (int i = 0; i < n2.length; i++){
            n2_copy[i] = Character.getNumericValue(n2[i]);
        }

        // 자리수에 맞게 연산
        for (int i = 0; i < size; i++){
            for (int k = 0; k < len; k++){
                hap[hap.length - (count + 1)] += n1_copy[n1_copy.length - (k + 1)] * n2_copy[n2_copy.length - (i + 1)];
                count++;
            }
            count = (i + 1);
        }

        // 각자리수에서 나온 결과값 올림수
        while (true){
            while (hap[hap.length - (n + 1)] > 9 && hap.length - (n + 1) > 0){
                hap[hap.length - (n + 1)] -= 10;
                hap[hap.length - (n + 2)] += 1;
            }
            n++;
            if (hap.length - (n + 1) <= 0){
                break;
            }
        }

        String result = Remove_Zero(hap);
        return result;
    }

    public static String Division(char[] n1, char[] n2) {

        int num = 0;
        int count = 0;
        String min = "";
        String result = "";
        char[] data = new char[n1.length];
        String test = "";
        char[] data_copy = new char[data.length];

        // 만약 나눌숫자가 나눠지는 수가 0이면 0을 return
        if (n1[0] == '0' || n2[0] == '0') {
            result = "0";
            return result;
        }

        for (int i = 0; i < n1.length; i++) {

            // 하나씩 숫자 늘려가며 계산할 배열 재할당
            data = new char[i + 1];

            // 첫번째는 자체적으로 인덱스에 값 할당
            if (i == 0) {
                data[i] = n1[i];
            }

            // 그 이후부터는 자동으로 n1의 값을 data에 할당
            while (count <= i) {
                data[count] = n1[count];
                count++;
            }

            data_copy = data;

            // 임시로 test에 값 추가
            min = Minus(data, n2);
            test = min;
            min = "";

            // 양수가 나오면 나누는 수가 더 크기에 넘겨주고 음수나 0이 나오면 나눠지는 값을 나누는 수만큼 빼주기
            while (true) {
                num++;
                min = Minus(data, n2);
                data = min.toCharArray();

                if (min.charAt(0) == '-') {
                    min = test;
                    num--;
                    break;
                }
                test = min;
            }

            data = data_copy;

            // 나머지를 넣어주는 코드
            if (min.length() >= 1 && min.charAt(0) != '-') {

                // n1의 나머지 값
                char[] n1_copy = new char[n1.length - data.length];
                for (int k = 0; k < n1_copy.length; k++) {
                    n1_copy[n1_copy.length - (k + 1)] = n1[n1.length - (k + 1)];
                }

                for (int k = 0; k < n1.length; k++) {
                    n1[k] = '0';
                }

                data = new char[n1_copy.length + min.length()];

                for (int k = 0; k < n1_copy.length; k++) {
                    data[data.length - (k + 1)] = n1_copy[n1_copy.length - (k + 1)];
                }

                for (int k = 0; k < min.length(); k++) {
                    data[k] = min.charAt(k);
                }

                for (int k = 0; k < data.length; k++) {
                    n1[n1.length - (k + 1)] = data[data.length - (k + 1)];
                }

            }

            // 0을 제거해주는 코드
            String str = String.valueOf(num);

            char[] strarray = str.toCharArray();

            int[] int_array = new int[strarray.length];

            for (int s = 0; s < strarray.length; s++) {
                int_array[s] = Character.getNumericValue(strarray[s]);
            }

            String value = Remove_Zero(int_array);

            result += value;

            count = 0;
            num = 0;

        }

        // 0을 제거해주는 코드
        String str = result;

        char[] strarray = str.toCharArray();

        int[] int_array = new int[strarray.length];

        for (int s = 0; s < strarray.length; s++) {
            int_array[s] = Character.getNumericValue(strarray[s]);
        }

        result = Remove_Zero(int_array);

        return result;
    }

    public static String Remaining(char[] n1, char[] n2){

        int count = 0;
        String min ="";
        String result = "";
        char [] data = new char[n1.length];
        String test = "";
        char [] data_copy = new char[data.length];

        // 만약 나눌숫자가 나눠지는 수가 0이면 0을 return
        if (n1[0] == '0' || n2[0] == '0'){
            result = "0";
            return result;
        }

        for (int i = 0; i < n1.length; i++) {

            // 하나씩 숫자 늘려가며 계산할 배열 재할당
            data = new char[i + 1];

            // 첫번째는 자체적으로 인덱스에 값 할당
            if (i == 0) {
                data[i] = n1[i];
            }

            // 그 이후부터는 자동으로 n1의 값을 data에 할당
            while (count <= i) {
                data[count] = n1[count];
                count++;
            }

            data_copy = data;

            // 임시로 test에 값 추가
            min = Minus(data, n2);
            test = min;
            min = "";

            // 양수가 나오면 나누는 수가 더 크기에 넘겨주고 음수나 0이 나오면 나눠지는 값을 나누는 수만큼 빼주기
            while (true) {

                min = Minus(data, n2);
                data = min.toCharArray();

                if (min.charAt(0) == '-') {
                    min = test;
                    break;
                }
                test = min;
            }

            data = data_copy;

            // 나머지를 넣어주는 코드
            if (min.length() >= 1 && min.charAt(0) != '-'){

                // n1의 나머지 값
                char [] n1_copy = new char [n1.length - data.length];
                for(int k = 0; k < n1_copy.length; k++){
                    n1_copy[n1_copy.length - (k + 1)] = n1[n1.length - (k + 1)];
                }

                for(int k = 0; k < n1.length; k++){
                    n1[k] = '0';
                }

                data = new char[n1_copy.length + min.length()];

                for(int k = 0; k < n1_copy.length; k++){
                    data[data.length - (k + 1)] = n1_copy[n1_copy.length - (k + 1)];
                }

                for (int k = 0; k < min.length(); k++){
                    data[k] = min.charAt(k);
                }

                for(int k = 0; k < data.length; k++){
                    n1[n1.length - (k + 1)] = data[data.length - (k + 1)];
                }

            }

            // 나머지가 딱떨어지면 0으로 해줌
            if (min.charAt(0) == '-'){
                min = "0";
            }

            result = min;

            char[] strarray = min.toCharArray();

            int[] array = new int[strarray.length];

            for (int s = 0; s < strarray.length; s++) {
                array[s] = Character.getNumericValue(strarray[s]);
            }

            result = Remove_Zero(array);

            count = 0;

        }

        // 0을 제거해주는 코드
        String str = result;

        char[] strarray = str.toCharArray();

        int[] array = new int[strarray.length];

        for (int s = 0; s < strarray.length; s++) {
            array[s] = Character.getNumericValue(strarray[s]);
        }

        result = Remove_Zero(array);

        return result;
    }

    public static String Remove_Zero(int[] hap){

        // 리스트에 배열값 복사
        ArrayList<Integer> copy = new ArrayList<>();
        for (int i = 0; i < hap.length; i++){
            copy.add(hap[i]);
        }

        // 맨 앞 원소가 0이면 제거하는 while문
        while (copy.size() > 1 && copy.get(0) == 0){
            copy.remove(0);
            if (copy.get(0) != 0){
                break;
            }
        }

        StringBuilder last = new StringBuilder();
        for (int num : copy){
            last.append(num);
        }

        String result = last.toString();

        return result;
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        // 필요 변수들 선언
        String result = "";
        String n1 = "";
        String n2 = "";
        char operator = '\u0000';
        int count = 0;

        System.out.print("수식을 입력해주세요: ");

        // 수식받을 origin 변수 선언
        String origin =  scanner.nextLine();

        // origin 공백제거
        origin = origin.replaceAll("\\s", "");

        // 배열로 origin 내용할당
        char [] or_array = origin.toCharArray();

        // operator 기호대입
        for (int i = 0; i < or_array.length; i++){
            if (or_array[i] == '+' || or_array[i] == '-' || or_array[i] == '*' || or_array[i] == '/' || or_array[i] == '%'){
                operator += or_array[i];
                count = i;
            }
        }

        // n1과 n2에 값대입
        for (int i = 0; i < count; i++){
            if (or_array[i] >= '0' && or_array[i] <= '9'){
                n1 += or_array[i];
            }
        }

        for (int i = count + 1; i < or_array.length; i++){
            if (or_array[i] >= '0' && or_array[i] <= '9'){
                n2 += or_array[i];
            }
        }

        char[] a = n1.toCharArray();
        char[] b = n2.toCharArray();

        // 숫자형 배열 선언
        int[] n1_copy = new int[a.length];
        int[] n2_copy = new int[b.length];

        // 문자열을 숫자로 바꿔주기
        for(int i = 0; i < n1_copy.length; i++){
            n1_copy[i] = Character.getNumericValue(a[i]);
        }

        for(int i = 0; i < n2_copy.length; i++){
            n2_copy[i] = Character.getNumericValue(b[i]);
        }

        // 리턴값을 받을 문자열 생성
        String value = Remove_Zero(n1_copy);
        a = new char[value.length()];

        // 문자열을 문자형 배열에 할당
        for(int i = 0; i < value.length(); i++){
            a[i] = value.charAt(i);
        }

        // Line 650과 동일
        String value2 = Remove_Zero(n2_copy);
        b = new char[value2.length()];

        // Line 654와 동일
        for(int i = 0; i < value2.length(); i++){
            b[i] = value2.charAt(i);
        }

        // switch case
        switch (operator) {
            case '+':
                result = Plus(a, b);
                break;
            case '-':
                result = Minus(a, b);
                break;

            case '*':
                result = Multiply(a, b);
                break;

            case '/':
                result = Division(a, b);
                break;

            case '%':
                result =Remaining(a, b);
                break;
        }

        System.out.println(result);
        scanner.close();
    }
}
