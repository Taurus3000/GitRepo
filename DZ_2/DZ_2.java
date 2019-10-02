public class DZ_2 {
    public static void main(String[] args)
            throws MyArraySizeException, MyArrayDataException {
        String str = new String("10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0");
        System.out.println("Полусумма матрицы: " + HalfSum(str));
    }
    //Метод преобразует строку в двумерный массив,
    //элементы преобразует в числа и находит их полусумму,
    //бросает исключения, если матрица не 4х4 и если в её ячейке не число

    static int HalfSum(String s)
            throws MyArraySizeException, MyArrayDataException {
            int sum = 0;
            String[][] strArr = new String[4][4];
            String[] parts = s.split("\n");  //Делим строку на куски без переносов

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    String[] word = (parts[i]).split(" ");  //Куски строки делим на слова
                    if (4 != parts.length || 4 != word.length) {
                        throw new MyArraySizeException();
                    }
                    try {
                        strArr[i][j] = word[j];
                        sum += Integer.parseInt(strArr[i][j]);  //Преобразуем элементы в числа и суммируем
                        System.out.print(strArr[i][j] + " "); //Для проверки
                    }  catch (NumberFormatException e) {
                        throw new MyArrayDataException();
                    }
                }
                System.out.println(); //Для проверки
                System.out.println("часть: " + parts[i]); //Для проверки
            }
            return sum / 2;
        }
    }
