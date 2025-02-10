using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[CreateAssetMenu(fileName = "new Weapon asset", menuName = "ScriptableObjects/Weapon")]

public class WeaponObject : ScriptableObject
{
    public string name;
    public int velocity;
    public float bulletMass;
    public float fireRate;
    public int magSize;
    public enum FireMode { Single, Auto }

    public FireMode fireMode;
}
