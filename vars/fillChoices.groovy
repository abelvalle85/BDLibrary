def call(String path){
    def servers=[]
    new File(path).eachLine{ line->
        servers << line
    }
    println servers
}