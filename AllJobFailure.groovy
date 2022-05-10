Jenkins.instance.getAllItems(Job).each{
def jobBuilds=it.getBuilds().findAll {job -> job.result == Result.FAILURE}
  if (jobBuilds.result) {
    println "${it.fullName}"
  }
}
return
