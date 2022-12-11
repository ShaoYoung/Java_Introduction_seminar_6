public class Cat {
// для информационной системой ветеринарной клиники
        int id;
        String name, breed, color;
        int age, weight;
        boolean gender;

//        конструктор
        public Cat(int id, String name, String breed, String color, boolean gender, int weight, int age) {
                this.id = id;
                this.name = name;
                this.gender = gender;
                this.breed = breed;
                this.color = color;
                this.weight = weight;
                this.age = age;
        }

        public String toString(int number) {
                String temp = this.gender ? "Male" : "Female";
                return "Cat's number: " + number + "\n" +
                        "Name: " + name + "\n" +
                        "Gender: " + temp + "\n" +
                        "Breed: " + breed + "\n" +
                        "Colour: " + color + "\n" +
                        "Weight: " + weight + "\n" +
                        "Age: " + age + "\n";
        }


        public static StringBuilder getCatsByGender(Cat[] cats, boolean gender){
                StringBuilder result = new StringBuilder();
                for (Cat c: cats) {
                        if (c.gender==gender){
                                result.append(c.name);
                                result.append(" ");
                        }
                }
                return result;
        }



}
