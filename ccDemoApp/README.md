# AWS CLI Commands for Provisioning ccDemoApp Infra

## IAM Roles
```
aws iam create-role --role-name YOUR_ROLE_NAME --assume-role-policy-document file://EC2TrustPolicy.json

aws iam attach-role-policy --role-name YOUR_ROLE_NAME  --policy-arn arn:aws:iam::aws:policy/service-role/AmazonEC2RoleforAWSCodeDeploy

aws iam create-role --role-name YOUR_ROLE_NAME --assume-role-policy-document file://CodeDeployTrustPolicy.json

aws iam attach-role-policy --role-name YOUR_ROLE_NAME  --policy-arn arn:aws:iam::aws:policy/service-role/AWSYOUR_ROLE_NAME
```

## Security Group, Ingress Rules
```
aws ec2 describe-vpcs

aws ec2 describe-subnets

aws ec2 create-security-group \
   --group-name ccSG \
   --description "ccSG" \
   --tag-specifications 'ResourceType=security-group,Tags=[{Key=Name,Value=cc-sg}]' \
   --vpc-id "YOUR_VPC_ID" 

aws ec2 authorize-security-group-ingress \
  --group-id YOUR_SG_ID \
  --protocol tcp \
  --port 22 \
  --cidr 0.0.0.0/0 
  
aws ec2 authorize-security-group-ingress \
  --group-id YOUR_SG_ID \
  --protocol tcp \
  --port 80 \
  --cidr 0.0.0.0/0 

aws ec2 authorize-security-group-ingress \
  --group-id YOUR_SG_ID \
  --protocol tcp \
  --port 8080 \
  --cidr 0.0.0.0/0 
```

## EC2 Instances
```
aws iam create-instance-profile --instance-profile-name YOUR_PROFILE_NAME

aws iam add-role-to-instance-profile --role-name YOUR_ROLE_NAME --instance-profile-name YOUR_PROFILE_NAME

aws ec2 create-key-pair --key-name YOUR_KP_NAME --output text > ~/.ssh/cc-key

// AMI for us-east-1
aws ec2 describe-images \
  --owners amazon \
  --region YOUR_REGION \
  --filters "Name=name,Values=amzn2-ami-kernel-5.10-hvm-2.0.20230221.0-x86_64-gp2" "Name=root-device-type,Values=ebs"

// Dev Servers
aws ec2 run-instances \
--image-id YOUR_AMI_ID \
--count 2 \
--instance-type t2.micro \
--key-name YOUR_KP_NAME \
--iam-instance-profile Name=YOUR_PROFILE_NAME \
--security-group-ids YOUR_SG_ID \
--subnet-id YOUR_SUBNET_ID \
--block-device-mappings "[{\"DeviceName\":\"/dev/sdf\",\"Ebs\":{\"VolumeSize\":10,\"DeleteOnTermination\":false}}]" \
--tag-specifications 'ResourceType=instance,Tags=[{Key=Name,Value=appserver}, {Key=Environment,Value=Dev}]' \
--user-data file://code_deploy_user_data.txt 

// Prod Servers
aws ec2 run-instances \
--image-id YOUR_AMI_ID \
--count 2 \
--instance-type t2.micro \
--key-name YOUR_KP_NAME \
--iam-instance-profile Name=YOUR_PROFILE_NAME \
--security-group-ids YOUR_SG_ID \
--subnet-id YOUR_SUBNET_ID \
--block-device-mappings "[{\"DeviceName\":\"/dev/sdf\",\"Ebs\":{\"VolumeSize\":10,\"DeleteOnTermination\":false}}]" \
--tag-specifications 'ResourceType=instance,Tags=[{Key=Name,Value=appserver}, {Key=Environment,Value=Prod}]' \
--user-data file://code_deploy_user_data.txt 

sudo service codedeploy-agent status
```
