# AWS CLI Commands for CloudFormation

## Create CodePipeline Stack(s)

### CodeCommit
```
aws cloudformation validate-template --template-body file://1_CodeCommit.yml 

aws cloudformation create-stack --stack-name ccDemoAppRepo --template-body file://1_CodeCommit.yml --parameters file://params/1_CodeCommit-params.json

aws cloudformation describe-stacks --stack-name ccDemoAppRepo

aws cloudformation describe-stacks --stack-name ccDemoAppRepo | grep StackStatus

aws cloudformation list-stack-resources --stack-name ccDemoAppRepo
```

### S3 Artifact Bucket
```
aws cloudformation validate-template --template-body file://2_S3ArtifactBucket.yml 

aws cloudformation create-stack --stack-name ccDemoAppBucket --template-body file://2_S3ArtifactBucket.yml --parameters file://params/2_S3ArtifactBucket-params.json 

aws cloudformation describe-stacks --stack-name ccDemoAppBucket | grep StackStatus
```

### CodeBuild
```
aws cloudformation validate-template --template-body file://3_CodeBuild.yml

aws cloudformation create-stack --stack-name ccDemoCodeBuild --template-body file://3_CodeBuild.yml --parameters file://params/3_CodeBuild-params.json --capabilities CAPABILITY_IAM --capabilities CAPABILITY_NAMED_IAM

aws cloudformation describe-stacks --stack-name ccDemoCodeBuild | grep StackStatus
```

### CodeDeploy
```
aws cloudformation validate-template --template-body file://4_CodeDeploy.yml

aws cloudformation create-stack --stack-name ccDemoCodeDeploy --template-body file://4_CodeDeploy.yml --parameters file://params/4_CodeDeploy-params.json --capabilities CAPABILITY_IAM --capabilities CAPABILITY_NAMED_IAM

aws cloudformation describe-stacks --stack-name ccDemoCodeDeploy | grep StackStatus
```

### CodePipeline
```
aws cloudformation validate-template --template-body file://5_CodePipeline.yml

aws cloudformation create-stack --stack-name ccDemoCodePipeline --template-body file://5_CodePipeline.yml --parameters file://params/5_CodePipeline-params.json  --capabilities CAPABILITY_IAM --capabilities CAPABILITY_NAMED_IAM

aws cloudformation describe-stacks --stack-name ccDemoCodePipeline | grep StackStatus
```


## Delete Stacks
```
aws cloudformation delete-stack --stack-name ccDemoCodePipeline

aws cloudformation delete-stack --stack-name ccDemoCodeDeploy

aws cloudformation delete-stack --stack-name ccDemoCodeBuild

aws cloudformation delete-stack --stack-name ccDemoAppBucket

aws cloudformation delete-stack --stack-name ccDemoAppRepo
```
