def call(String filepath){
   /* List<String> readFileInList(String filePath) {
        File file = new File(filePath)
        def lines = file.readLines()
        return lines
    }*/
    //def parameters = []
    File file = new File(filepath)
    def lines = file.readLines()
    return lines
    /*new File(filepath).eachLine { line ->
        parameters << line
    }*/
    //return parameters
    /*parameters.each {
        println it
    }*/
}