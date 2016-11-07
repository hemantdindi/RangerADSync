# RangerADSync
Ranger AD Sync

RangerADSync is an alternative to Ranger UserSync, focussed on AD+Ranger integration. The biggest security concern with usersync is that it gets all the user details from AD into its database and then performs the look up operation. But ideal scenario should be the other way i.e. lookup should happen first on AD and on success the user/groups entry should happen in Ranger DB. 

Also, in large enterprises spanned across globe, the number of users/accounts in their AD might be in tens of thousands. Integrating the entire AD with Ranger and ensuring complete AD Sync, might give nightmares to Operations/Support team. For ex, if the Hadoop support/applications team need only 200 policies for n use cases, then the x_user and x_group table of ranger database should have 200 users and 200 groups assuming 1 user/1 Group per use case. But with existing Ranger UserSync with AD integration, the number of records in both table might be in 1000's.


This is what exactly RangerADSync fixes. RangerADSync sync's only the users and groups for which policies have to be created. 

Coding in progress
 Currently working on adding logging mechanism using log4j


