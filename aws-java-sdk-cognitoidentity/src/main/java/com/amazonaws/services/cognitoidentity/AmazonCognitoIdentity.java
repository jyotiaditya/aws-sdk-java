/*
 * Copyright 2010-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.cognitoidentity;

import com.amazonaws.*;
import com.amazonaws.regions.*;

import com.amazonaws.services.cognitoidentity.model.*;

/**
 * Interface for accessing Amazon Cognito Identity.
 * <p>
 * <fullname>Amazon Cognito</fullname>
 * <p>
 * Amazon Cognito is a web service that delivers scoped temporary credentials to
 * mobile devices and other untrusted environments. Amazon Cognito uniquely
 * identifies a device and supplies the user with a consistent identity over the
 * lifetime of an application.
 * </p>
 * <p>
 * Using Amazon Cognito, you can enable authentication with one or more
 * third-party identity providers (Facebook, Google, or Login with Amazon), and
 * you can also choose to support unauthenticated access from your app. Cognito
 * delivers a unique identifier for each user and acts as an OpenID token
 * provider trusted by AWS Security Token Service (STS) to access temporary,
 * limited-privilege AWS credentials.
 * </p>
 * <p>
 * To provide end-user credentials, first make an unsigned call to <a>GetId</a>.
 * If the end user is authenticated with one of the supported identity
 * providers, set the <code>Logins</code> map with the identity provider token.
 * <code>GetId</code> returns a unique identifier for the user.
 * </p>
 * <p>
 * Next, make an unsigned call to <a>GetCredentialsForIdentity</a>. This call
 * expects the same <code>Logins</code> map as the <code>GetId</code> call, as
 * well as the <code>IdentityID</code> originally returned by <code>GetId</code>
 * . Assuming your identity pool has been configured via the
 * <a>SetIdentityPoolRoles</a> operation, <code>GetCredentialsForIdentity</code>
 * will return AWS credentials for your use. If your pool has not been
 * configured with <code>SetIdentityPoolRoles</code>, or if you want to follow
 * legacy flow, make an unsigned call to <a>GetOpenIdToken</a>, which returns
 * the OpenID token necessary to call STS and retrieve AWS credentials. This
 * call expects the same <code>Logins</code> map as the <code>GetId</code> call,
 * as well as the <code>IdentityID</code> originally returned by
 * <code>GetId</code>. The token returned by <code>GetOpenIdToken</code> can be
 * passed to the STS operation <a href=
 * "http://docs.aws.amazon.com/STS/latest/APIReference/API_AssumeRoleWithWebIdentity.html"
 * >AssumeRoleWithWebIdentity</a> to retrieve AWS credentials.
 * </p>
 * <p>
 * If you want to use Amazon Cognito in an Android, iOS, or Unity application,
 * you will probably want to make API calls via the AWS Mobile SDK. To learn
 * more, see the <a href="http://docs.aws.amazon.com/mobile/index.html">AWS
 * Mobile SDK Developer Guide</a>.
 * </p>
 */
public interface AmazonCognitoIdentity {

    /**
     * Overrides the default endpoint for this client
     * ("https://cognito-identity.us-east-1.amazonaws.com"). Callers can use
     * this method to control which AWS region they want to work with.
     * <p>
     * Callers can pass in just the endpoint (ex:
     * "cognito-identity.us-east-1.amazonaws.com") or a full URL, including the
     * protocol (ex: "https://cognito-identity.us-east-1.amazonaws.com"). If the
     * protocol is not specified here, the default protocol from this client's
     * {@link ClientConfiguration} will be used, which by default is HTTPS.
     * <p>
     * For more information on using AWS regions with the AWS SDK for Java, and
     * a complete list of all available endpoints for all AWS services, see: <a
     * href=
     * "http://developer.amazonwebservices.com/connect/entry.jspa?externalID=3912"
     * > http://developer.amazonwebservices.com/connect/entry.jspa?externalID=
     * 3912</a>
     * <p>
     * <b>This method is not threadsafe. An endpoint should be configured when
     * the client is created and before any service requests are made. Changing
     * it afterwards creates inevitable race conditions for any service requests
     * in transit or retrying.</b>
     *
     * @param endpoint
     *        The endpoint (ex: "cognito-identity.us-east-1.amazonaws.com") or a
     *        full URL, including the protocol (ex:
     *        "https://cognito-identity.us-east-1.amazonaws.com") of the region
     *        specific AWS endpoint this client will communicate with.
     */
    void setEndpoint(String endpoint);

    /**
     * An alternative to {@link AmazonCognitoIdentity#setEndpoint(String)}, sets
     * the regional endpoint for this client's service calls. Callers can use
     * this method to control which AWS region they want to work with.
     * <p>
     * By default, all service endpoints in all regions use the https protocol.
     * To use http instead, specify it in the {@link ClientConfiguration}
     * supplied at construction.
     * <p>
     * <b>This method is not threadsafe. A region should be configured when the
     * client is created and before any service requests are made. Changing it
     * afterwards creates inevitable race conditions for any service requests in
     * transit or retrying.</b>
     *
     * @param region
     *        The region this client will communicate with. See
     *        {@link Region#getRegion(com.amazonaws.regions.Regions)} for
     *        accessing a given region. Must not be null and must be a region
     *        where the service is available.
     *
     * @see Region#getRegion(com.amazonaws.regions.Regions)
     * @see Region#createClient(Class,
     *      com.amazonaws.auth.AWSCredentialsProvider, ClientConfiguration)
     * @see Region#isServiceSupported(String)
     */
    void setRegion(Region region);

    /**
     * <p>
     * Creates a new identity pool. The identity pool is a store of user
     * identity information that is specific to your AWS account. The limit on
     * identity pools is 60 per account. The keys for
     * <code>SupportedLoginProviders</code> are as follows:
     * <ul>
     * <li>Facebook: <code>graph.facebook.com</code></li>
     * <li>Google: <code>accounts.google.com</code></li>
     * <li>Amazon: <code>www.amazon.com</code></li>
     * <li>Twitter: <code>api.twitter.com</code></li>
     * <li>Digits: <code>www.digits.com</code></li>
     * </ul>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param createIdentityPoolRequest
     *        Input to the CreateIdentityPool action.
     * @return Result of the CreateIdentityPool operation returned by the
     *         service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     * @throws LimitExceededException
     *         Thrown when the total number of user pools has exceeded a preset
     *         limit.
     */
    CreateIdentityPoolResult createIdentityPool(
            CreateIdentityPoolRequest createIdentityPoolRequest);

    /**
     * <p>
     * Deletes identities from an identity pool. You can specify a list of 1-60
     * identities that you want to delete.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param deleteIdentitiesRequest
     *        Input to the <code>DeleteIdentities</code> action.
     * @return Result of the DeleteIdentities operation returned by the service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     */
    DeleteIdentitiesResult deleteIdentities(
            DeleteIdentitiesRequest deleteIdentitiesRequest);

    /**
     * <p>
     * Deletes a user pool. Once a pool is deleted, users will not be able to
     * authenticate with the pool.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param deleteIdentityPoolRequest
     *        Input to the DeleteIdentityPool action.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     */
    void deleteIdentityPool(DeleteIdentityPoolRequest deleteIdentityPoolRequest);

    /**
     * <p>
     * Returns metadata related to the given identity, including when the
     * identity was created and any associated linked logins.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param describeIdentityRequest
     *        Input to the <code>DescribeIdentity</code> action.
     * @return Result of the DescribeIdentity operation returned by the service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     */
    DescribeIdentityResult describeIdentity(
            DescribeIdentityRequest describeIdentityRequest);

    /**
     * <p>
     * Gets details about a particular identity pool, including the pool name,
     * ID description, creation date, and current number of users.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param describeIdentityPoolRequest
     *        Input to the DescribeIdentityPool action.
     * @return Result of the DescribeIdentityPool operation returned by the
     *         service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     */
    DescribeIdentityPoolResult describeIdentityPool(
            DescribeIdentityPoolRequest describeIdentityPoolRequest);

    /**
     * <p>
     * Returns credentials for the provided identity ID. Any provided logins
     * will be validated against supported login providers. If the token is for
     * cognito-identity.amazonaws.com, it will be passed through to AWS Security
     * Token Service with the appropriate role for the token.
     * </p>
     * <p>
     * This is a public API. You do not need any credentials to call this API.
     * </p>
     * 
     * @param getCredentialsForIdentityRequest
     *        Input to the <code>GetCredentialsForIdentity</code> action.
     * @return Result of the GetCredentialsForIdentity operation returned by the
     *         service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InvalidIdentityPoolConfigurationException
     *         Thrown if the identity pool has no role associated for the given
     *         auth type (auth/unauth) or if the AssumeRole fails.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     * @throws ExternalServiceException
     *         An exception thrown when a dependent service such as Facebook or
     *         Twitter is not responding
     */
    GetCredentialsForIdentityResult getCredentialsForIdentity(
            GetCredentialsForIdentityRequest getCredentialsForIdentityRequest);

    /**
     * <p>
     * Generates (or retrieves) a Cognito ID. Supplying multiple logins will
     * create an implicit linked account.
     * </p>
     * <p>
     * This is a public API. You do not need any credentials to call this API.
     * </p>
     * 
     * @param getIdRequest
     *        Input to the GetId action.
     * @return Result of the GetId operation returned by the service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     * @throws LimitExceededException
     *         Thrown when the total number of user pools has exceeded a preset
     *         limit.
     * @throws ExternalServiceException
     *         An exception thrown when a dependent service such as Facebook or
     *         Twitter is not responding
     */
    GetIdResult getId(GetIdRequest getIdRequest);

    /**
     * <p>
     * Gets the roles for an identity pool.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param getIdentityPoolRolesRequest
     *        Input to the <code>GetIdentityPoolRoles</code> action.
     * @return Result of the GetIdentityPoolRoles operation returned by the
     *         service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     */
    GetIdentityPoolRolesResult getIdentityPoolRoles(
            GetIdentityPoolRolesRequest getIdentityPoolRolesRequest);

    /**
     * <p>
     * Gets an OpenID token, using a known Cognito ID. This known Cognito ID is
     * returned by <a>GetId</a>. You can optionally add additional logins for
     * the identity. Supplying multiple logins creates an implicit link.
     * </p>
     * <p>
     * The OpenId token is valid for 15 minutes.
     * </p>
     * <p>
     * This is a public API. You do not need any credentials to call this API.
     * </p>
     * 
     * @param getOpenIdTokenRequest
     *        Input to the GetOpenIdToken action.
     * @return Result of the GetOpenIdToken operation returned by the service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     * @throws ExternalServiceException
     *         An exception thrown when a dependent service such as Facebook or
     *         Twitter is not responding
     */
    GetOpenIdTokenResult getOpenIdToken(
            GetOpenIdTokenRequest getOpenIdTokenRequest);

    /**
     * <p>
     * Registers (or retrieves) a Cognito <code>IdentityId</code> and an OpenID
     * Connect token for a user authenticated by your backend authentication
     * process. Supplying multiple logins will create an implicit linked
     * account. You can only specify one developer provider as part of the
     * <code>Logins</code> map, which is linked to the identity pool. The
     * developer provider is the "domain" by which Cognito will refer to your
     * users.
     * </p>
     * <p>
     * You can use <code>GetOpenIdTokenForDeveloperIdentity</code> to create a
     * new identity and to link new logins (that is, user credentials issued by
     * a public provider or developer provider) to an existing identity. When
     * you want to create a new identity, the <code>IdentityId</code> should be
     * null. When you want to associate a new login with an existing
     * authenticated/unauthenticated identity, you can do so by providing the
     * existing <code>IdentityId</code>. This API will create the identity in
     * the specified <code>IdentityPoolId</code>.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param getOpenIdTokenForDeveloperIdentityRequest
     *        Input to the <code>GetOpenIdTokenForDeveloperIdentity</code>
     *        action.
     * @return Result of the GetOpenIdTokenForDeveloperIdentity operation
     *         returned by the service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     * @throws DeveloperUserAlreadyRegisteredException
     *         The provided developer user identifier is already registered with
     *         Cognito under a different identity ID.
     */
    GetOpenIdTokenForDeveloperIdentityResult getOpenIdTokenForDeveloperIdentity(
            GetOpenIdTokenForDeveloperIdentityRequest getOpenIdTokenForDeveloperIdentityRequest);

    /**
     * <p>
     * Lists the identities in a pool.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param listIdentitiesRequest
     *        Input to the ListIdentities action.
     * @return Result of the ListIdentities operation returned by the service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     */
    ListIdentitiesResult listIdentities(
            ListIdentitiesRequest listIdentitiesRequest);

    /**
     * <p>
     * Lists all of the Cognito identity pools registered for your account.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param listIdentityPoolsRequest
     *        Input to the ListIdentityPools action.
     * @return Result of the ListIdentityPools operation returned by the
     *         service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     */
    ListIdentityPoolsResult listIdentityPools(
            ListIdentityPoolsRequest listIdentityPoolsRequest);

    /**
     * <p>
     * Retrieves the <code>IdentityID</code> associated with a
     * <code>DeveloperUserIdentifier</code> or the list of
     * <code>DeveloperUserIdentifier</code>s associated with an
     * <code>IdentityId</code> for an existing identity. Either
     * <code>IdentityID</code> or <code>DeveloperUserIdentifier</code> must not
     * be null. If you supply only one of these values, the other value will be
     * searched in the database and returned as a part of the response. If you
     * supply both, <code>DeveloperUserIdentifier</code> will be matched against
     * <code>IdentityID</code>. If the values are verified against the database,
     * the response returns both values and is the same as the request.
     * Otherwise a <code>ResourceConflictException</code> is thrown.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param lookupDeveloperIdentityRequest
     *        Input to the <code>LookupDeveloperIdentityInput</code> action.
     * @return Result of the LookupDeveloperIdentity operation returned by the
     *         service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     */
    LookupDeveloperIdentityResult lookupDeveloperIdentity(
            LookupDeveloperIdentityRequest lookupDeveloperIdentityRequest);

    /**
     * <p>
     * Merges two users having different <code>IdentityId</code>s, existing in
     * the same identity pool, and identified by the same developer provider.
     * You can use this action to request that discrete users be merged and
     * identified as a single user in the Cognito environment. Cognito
     * associates the given source user (<code>SourceUserIdentifier</code>) with
     * the <code>IdentityId</code> of the <code>DestinationUserIdentifier</code>
     * . Only developer-authenticated users can be merged. If the users to be
     * merged are associated with the same public provider, but as two different
     * users, an exception will be thrown.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param mergeDeveloperIdentitiesRequest
     *        Input to the <code>MergeDeveloperIdentities</code> action.
     * @return Result of the MergeDeveloperIdentities operation returned by the
     *         service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     */
    MergeDeveloperIdentitiesResult mergeDeveloperIdentities(
            MergeDeveloperIdentitiesRequest mergeDeveloperIdentitiesRequest);

    /**
     * <p>
     * Sets the roles for an identity pool. These roles are used when making
     * calls to <code>GetCredentialsForIdentity</code> action.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param setIdentityPoolRolesRequest
     *        Input to the <code>SetIdentityPoolRoles</code> action.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     * @throws ConcurrentModificationException
     *         Thrown if there are parallel requests to modify a resource.
     */
    void setIdentityPoolRoles(
            SetIdentityPoolRolesRequest setIdentityPoolRolesRequest);

    /**
     * <p>
     * Unlinks a <code>DeveloperUserIdentifier</code> from an existing identity.
     * Unlinked developer users will be considered new identities next time they
     * are seen. If, for a given Cognito identity, you remove all federated
     * identities as well as the developer user identifier, the Cognito identity
     * becomes inaccessible.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param unlinkDeveloperIdentityRequest
     *        Input to the <code>UnlinkDeveloperIdentity</code> action.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     */
    void unlinkDeveloperIdentity(
            UnlinkDeveloperIdentityRequest unlinkDeveloperIdentityRequest);

    /**
     * <p>
     * Unlinks a federated identity from an existing account. Unlinked logins
     * will be considered new identities next time they are seen. Removing the
     * last linked login will make this identity inaccessible.
     * </p>
     * <p>
     * This is a public API. You do not need any credentials to call this API.
     * </p>
     * 
     * @param unlinkIdentityRequest
     *        Input to the UnlinkIdentity action.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     * @throws ExternalServiceException
     *         An exception thrown when a dependent service such as Facebook or
     *         Twitter is not responding
     */
    void unlinkIdentity(UnlinkIdentityRequest unlinkIdentityRequest);

    /**
     * <p>
     * Updates a user pool.
     * </p>
     * <p>
     * You must use AWS Developer credentials to call this API.
     * </p>
     * 
     * @param updateIdentityPoolRequest
     *        An object representing a Cognito identity pool.
     * @return Result of the UpdateIdentityPool operation returned by the
     *         service.
     * @throws InvalidParameterException
     *         Thrown for missing or bad input parameter(s).
     * @throws ResourceNotFoundException
     *         Thrown when the requested resource (for example, a dataset or
     *         record) does not exist.
     * @throws NotAuthorizedException
     *         Thrown when a user is not authorized to access the requested
     *         resource.
     * @throws ResourceConflictException
     *         Thrown when a user tries to use a login which is already linked
     *         to another account.
     * @throws TooManyRequestsException
     *         Thrown when a request is throttled.
     * @throws InternalErrorException
     *         Thrown when the service encounters an error during processing the
     *         request.
     * @throws ConcurrentModificationException
     *         Thrown if there are parallel requests to modify a resource.
     */
    UpdateIdentityPoolResult updateIdentityPool(
            UpdateIdentityPoolRequest updateIdentityPoolRequest);

    /**
     * Shuts down this client object, releasing any resources that might be held
     * open. This is an optional method, and callers are not expected to call
     * it, but can if they want to explicitly release any open resources. Once a
     * client has been shutdown, it should not be used to make any more
     * requests.
     */
    void shutdown();

    /**
     * Returns additional metadata for a previously executed successful request,
     * typically used for debugging issues where a service isn't acting as
     * expected. This data isn't considered part of the result data returned by
     * an operation, so it's available through this separate, diagnostic
     * interface.
     * <p>
     * Response metadata is only cached for a limited period of time, so if you
     * need to access this extra diagnostic information for an executed request,
     * you should use this method to retrieve it as soon as possible after
     * executing a request.
     *
     * @param request
     *        The originally executed request.
     *
     * @return The response metadata for the specified request, or null if none
     *         is available.
     */
    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest request);
}
