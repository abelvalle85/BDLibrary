ArrayList<Integer> call(String path){
    def servers=[]
    new File(path).eachLine{ line->
        servers << line
    }
    return servers
}